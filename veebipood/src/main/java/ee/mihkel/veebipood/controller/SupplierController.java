package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.model.Supplier1;
import ee.mihkel.veebipood.model.Supplier2;
import ee.mihkel.veebipood.model.Supplier3;
import ee.mihkel.veebipood.model.Supplier3Book;
import ee.mihkel.veebipood.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("supplier1")
    public List<Supplier1> supplier1(){
        String credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        String principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println("Supplier1'te vaatab: " + credentials + " - " + principle);
        return supplierService.getSupplier1();
    }

    @GetMapping("supplier2")
    public List<Supplier2> supplier2(){
        return supplierService.getSupplier2();
    }

    @GetMapping("supplier3")
    public List<Supplier3Book> supplier3(){
        return supplierService.getSupplier3();
    }
}
