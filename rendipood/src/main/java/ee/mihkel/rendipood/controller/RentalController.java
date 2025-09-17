package ee.mihkel.rendipood.controller;

import ee.mihkel.rendipood.entity.Customer;
import ee.mihkel.rendipood.entity.Film;
import ee.mihkel.rendipood.entity.FilmType;
import ee.mihkel.rendipood.entity.Rental;
import ee.mihkel.rendipood.exception.FilmAlreadyRentedException;
import ee.mihkel.rendipood.exception.NotEnoughBonusPointsException;
import ee.mihkel.rendipood.exception.TooMuchBonusDaysException;
import ee.mihkel.rendipood.repository.CustomerRepository;
import ee.mihkel.rendipood.repository.FilmRepository;
import ee.mihkel.rendipood.repository.RentalRepository;
import ee.mihkel.rendipood.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RentalController {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    RentalService rentalService;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    CustomerRepository customerRepository;

    // localhost:8080/rental?id=1&bonusDays=3     BODY:
    @PostMapping("rental")
    public List<Rental> addRental(@RequestParam Long id, @RequestParam(required=false) Integer bonusDays,
                                  @RequestBody List<Film> films)
            throws NotEnoughBonusPointsException, FilmAlreadyRentedException, TooMuchBonusDaysException {
        if (bonusDays == null) {
            bonusDays = 0;
        }

        Customer customer = rentalService.getCustomer(id, bonusDays);

        List<Film> dbFilms = new ArrayList<>();
        int totalNewFilmDays = 0;
        for (Film f : films) {
            Film dbFilm = filmRepository.findById(f.getId()).orElseThrow();
            if (dbFilm.getDays() > 0 || dbFilm.getRental() != null) {
//                throw new RuntimeException("Film is already rented");
                throw new FilmAlreadyRentedException();
            }
            dbFilms.add(dbFilm);
            if (dbFilm.getType().equals(FilmType.NEW)) {
                totalNewFilmDays += dbFilm.getDays();
                customer.setBonuspoints(customer.getBonuspoints() - bonusDays * 25);
            }
        }

        if (totalNewFilmDays < bonusDays) {
//            throw new RuntimeException("Too much bonus days");
            throw new TooMuchBonusDaysException();
        }

        Rental rental = new Rental(); // {id: 0, initialFee: 0, lateFee: 0}
        rental.setCustomer(customer);
        Rental dbRental = rentalRepository.save(rental); // saaksime primaarvõtme kätte, et filmiga ühendada
        // {id: 2, initialFee: 0, lateFee: 0}

        double initialFee = rentalService.checkFilms(films, dbFilms, dbRental, customer);
        initialFee -= bonusDays * 4;
        dbRental.setInitialFee(initialFee);
        rentalRepository.save(dbRental); // leiame kogusumma ja siis lisame selle rentalile

        return rentalRepository.findAll();
    }

    @PutMapping("return-film")
    public Rental endRental(@RequestBody Film film) {
        Rental rental = film.getRental();
        Film dbFilm = filmRepository.findById(film.getId()).orElseThrow();

        double filmLateFee = rentalService.calculateLateFee(film.getDays(), dbFilm.getDays(), dbFilm.getType());
        rental.setLateFee(rental.getLateFee() + filmLateFee);

        dbFilm.setRental(null);
        dbFilm.setDays(0);
        filmRepository.save(dbFilm);
        return rental;
    }


}
