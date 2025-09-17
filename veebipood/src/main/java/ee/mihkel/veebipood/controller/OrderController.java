package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.model.ParcelMachine;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    // localhost:8080/orders?personId=3
    @PostMapping("orders")
    public List<Order> addOrder(@RequestParam Long personId, @RequestBody List<Product> products) {

        Order order = new Order();
        order.setProducts(products);
        order.setCreated(new Date());

        Person person = new Person();
        person.setId(personId);
        order.setPerson(person);

        double totalSum = 0;
        for (Product product : products) {
            Product dbProduct = productRepository.findById(product.getId()).orElseThrow();
            totalSum += dbProduct.getPrice();
        }
        order.setTotalSum(totalSum);

        orderRepository.save(order);
        return orderRepository.findAll();
    }

    @GetMapping("parcelmachines")
    public List<ParcelMachine> getParcelMachines() {
        return orderService.getParcelMachines();
    }

    @PostMapping("payment")
    public String makePayment(@RequestParam double sum) {
        return orderService.makePayment(sum);
    }

}
