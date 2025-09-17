package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.model.Supplier1;
import ee.mihkel.veebipood.model.Supplier2;
import ee.mihkel.veebipood.model.Supplier3;
import ee.mihkel.veebipood.model.Supplier3Book;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SupplierService {

    private double tax = 1.2;


    public List<Supplier1> getSupplier1() {
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        Supplier1[] response = restTemplate.exchange(url, HttpMethod.GET, null, Supplier1[].class).getBody();
        if (response == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(response)
                .filter(product -> product.getRating().getRate() > 3)
                .peek(product -> product.setRetailPrice(product.getPrice() * tax))
                .toList();
    }

    public List<Supplier2> getSupplier2() {
        String url = "https://api.escuelajs.co/api/v1/products";
        RestTemplate restTemplate = new RestTemplate();
        Supplier2[] response = restTemplate.exchange(url, HttpMethod.GET, null, Supplier2[].class).getBody();
        if (response == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(response)
                .peek(product -> product.setRetailPrice(product.getPrice() * tax))
                .toList();
    }

    public List<Supplier3Book> getSupplier3() {
        String url = "https://api.itbook.store/1.0/search/spring?page=0";
        RestTemplate restTemplate = new RestTemplate();
        Supplier3 response = restTemplate.exchange(url, HttpMethod.GET, null, Supplier3.class).getBody();
        if (response == null) {
            return new ArrayList<>();
        }
        return response.getBooks().stream()
                .peek(product -> product.setRetailPrice(
                        Double.parseDouble(product.getPrice().replace("$", "")) * 1.2
                ))
                .toList();
    }
}
