package ee.mihkel.rendipood.controller;

import ee.mihkel.rendipood.entity.Customer;
import ee.mihkel.rendipood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("signup")
    public List<Customer> signup(@RequestBody Customer customer) {
        customer.setBonuspoints(0);
        customerRepository.save(customer);
        return customerRepository.findAll();
    }

    @GetMapping("bonuspoints")
    public int getBonuspoints(@RequestParam Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return customer.getBonuspoints();
    }
}
