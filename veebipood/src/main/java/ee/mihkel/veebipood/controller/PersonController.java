package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    JwtTokenService jwtTokenService;

    @PostMapping("login")
    public String login() {
        return jwtTokenService.generateJwtToken();
    }


    @PostMapping("signup")
    public List<Person> signup(@RequestBody Person person) {
        personRepository.save(person);
        return personRepository.findAll();
    }

    @GetMapping("person")
    public Person getPerson() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return personRepository.findById(id).orElseThrow();
    }

}
