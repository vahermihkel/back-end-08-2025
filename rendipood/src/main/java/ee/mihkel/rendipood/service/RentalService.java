package ee.mihkel.rendipood.service;

import ee.mihkel.rendipood.entity.Customer;
import ee.mihkel.rendipood.entity.Film;
import ee.mihkel.rendipood.entity.FilmType;
import ee.mihkel.rendipood.entity.Rental;
import ee.mihkel.rendipood.exception.NotEnoughBonusPointsException;
import ee.mihkel.rendipood.repository.CustomerRepository;
import ee.mihkel.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final int PREMIUM_PRICE = 4;
    private final int BASIC_PRICE = 3;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomer(Long id, Integer bonusDays) throws NotEnoughBonusPointsException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        if (customer.getBonuspoints() < bonusDays * 25) {
//            throw new RuntimeException("Not enough bonus points");
            throw new NotEnoughBonusPointsException();
        }
        return customer;
    }

    public double checkFilms(List<Film> clientFilms, List<Film> dbFilms, Rental dbRental, Customer customer) {
        double initialFee = 0;
        for (int i = 0; i < clientFilms.size(); i++) {
//            Film film = filmRepository.findById(f.getId()).orElseThrow();
            dbFilms.get(i).setRental(dbRental);
            if (clientFilms.get(i).getDays() <= 0) {
                throw new RuntimeException("Days must be greater than 0");
            }
            dbFilms.get(i).setDays(clientFilms.get(i).getDays());
            filmRepository.save(dbFilms.get(i));

            initialFee = calculateInitialFee(dbFilms.get(i), initialFee, customer);
        }
        return initialFee;
    }

    // 3 päeva boonust         250punkti
    private double calculateInitialFee(Film film, double initialFee, Customer customer) {
        switch (film.getType()){
            case NEW -> {
                customer.setBonuspoints(customer.getBonuspoints() + 2);
                initialFee += PREMIUM_PRICE * film.getDays();
            }
            case REGULAR -> {
                customer.setBonuspoints(customer.getBonuspoints() + 1);
                initialFee = calculateFilmCost(film, 3, initialFee);
            }
            case OLD -> {
                customer.setBonuspoints(customer.getBonuspoints() + 1);
                initialFee = calculateFilmCost(film, 5, initialFee);
            }
        }
        return initialFee;
    }

    private double calculateFilmCost(Film film, int freeDays, double initialFee) {
        if (film.getDays() <= freeDays) {
            initialFee += BASIC_PRICE;
        } else {
            initialFee += BASIC_PRICE + BASIC_PRICE * (film.getDays() - freeDays);
        }
        return initialFee;
    }

    public double calculateLateFee(int realDays, int initialDays, FilmType filmType) {
        if (realDays <= initialDays) {
            return 0;
        } else {
            switch (filmType) {
                case NEW -> {
                    return (realDays - initialDays) * PREMIUM_PRICE;
                }
                case REGULAR, OLD -> {
                    return (realDays - initialDays) * BASIC_PRICE;
                }
                case null, default -> {
                    return 0;
                }
            }
        }
    }
}
