package ee.mihkel.rendipood.service;

import ee.mihkel.rendipood.entity.Customer;
import ee.mihkel.rendipood.entity.Film;
import ee.mihkel.rendipood.entity.FilmType;
import ee.mihkel.rendipood.entity.Rental;
import ee.mihkel.rendipood.exception.NotEnoughBonusPointsException;
import ee.mihkel.rendipood.repository.CustomerRepository;
import ee.mihkel.rendipood.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RentalServiceTest {

//    @Autowired
//    private RentalService service;

    @Mock
    FilmRepository filmRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    RentalService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkFilms() {
    }

    // unit testing naming conventions

    // mis tingimused täidetud _ mida teen _ mis juhtub
    // while_when_then
    @Test
    void whileRegularFilm_whenOneDayOver_lateFeeIsBasicPrice() {
        double lateFee = service.calculateLateFee(2, 1, FilmType.REGULAR);
        assertEquals(3, lateFee);
    }

    @Test
    void whileNewFilm_whenTwoDaysOver_lateFeeIsDoubleThePremiumPrice() {
        double lateFee = service.calculateLateFee(3, 1, FilmType.NEW);
        assertEquals(8, lateFee);
    }

    @Test
    void whileRegularFilm_whenNoDaysOver_lateFeeIsZero() {
        double lateFee = service.calculateLateFee(2, 3, FilmType.REGULAR);
        assertEquals(0, lateFee);
    }

    @Test
    void initialFee() {
        double initialFee = service.checkFilms(new ArrayList<>(), new ArrayList<>(), new Rental(), new Customer());
        assertEquals(0, initialFee);
    }

    @Test
    void givenDaysAreZero_whenCheckingFilms_thenThrowsException() {
        List<Film> clientFilms = new ArrayList<>();
        clientFilms.add(new Film());

        List<Film> dbFilms = new ArrayList<>();
        dbFilms.add(new Film());

        assertThrows(RuntimeException.class, () -> service.checkFilms(clientFilms, dbFilms, new Rental(), new Customer()));
    }

    @Test
    void givenFilmIsNew_whenRentingForTwoDays_thenInitialIsEight() {
        List<Film> clientFilms = new ArrayList<>();
        Film clientFilm = new Film();
        clientFilm.setDays(2);
        clientFilms.add(clientFilm);

        List<Film> dbFilms = new ArrayList<>();
        Film dbFilm = new Film();
        dbFilm.setType(FilmType.NEW);
        dbFilms.add(dbFilm);


        double initialFee = service.checkFilms(clientFilms, dbFilms, new Rental(), new Customer());
        assertEquals(8, initialFee);
    }

    @Test
    void getCustomer() throws NotEnoughBonusPointsException {
        Customer customer = new Customer();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        assertThrows(NotEnoughBonusPointsException.class, () -> service.getCustomer(1L, 2));
    }
}