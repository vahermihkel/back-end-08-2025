package ee.mihkel.rendipood.controller;

import ee.mihkel.rendipood.entity.Film;
import ee.mihkel.rendipood.entity.FilmType;
import ee.mihkel.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @PostMapping("film")
    public Film createFilm(@RequestBody Film film){
        film.setDays(0);
        film.setRental(null);
        return filmRepository.save(film);
    }

    // PathVariable: localhost:8080/film/2
    // RequestParam: localhost:8080/film?id=4
    @DeleteMapping("film")
    public void deleteFilm(@RequestParam Long id){
        filmRepository.deleteById(id);
    }

    @PutMapping("film")
    public Film updateFilm(@RequestParam Long id, @RequestParam String newType){
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(FilmType.valueOf(newType));
        return filmRepository.save(film);
    }

    @GetMapping("film")
    public List<Film> getFilmList(){
        return filmRepository.findAll();
    }

    @GetMapping("available-films")
    public List<Film> getAvailableFilmList(){
//        List<Film> films = filmRepository.findAll();
//        List<Film> availableFilms = new ArrayList<>();
//        for (Film f: films) {
//            if (f.getRental() == null) {
//                availableFilms.add(f);
//            }
//        }
//        return availableFilms;
        return filmRepository.findByRentalNull();
    }
}
