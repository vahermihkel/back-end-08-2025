package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.model.EveryPayBody;
import ee.mihkel.veebipood.model.EveryPayResponse;
import ee.mihkel.veebipood.model.ParcelMachine;
import ee.mihkel.veebipood.model.Supplier1;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    public List<ParcelMachine> getParcelMachines() {
        String url = "https://www.omniva.ee/locations.json";
        RestTemplate restTemplate = new RestTemplate();
        ParcelMachine[] response = restTemplate.exchange(url, HttpMethod.GET, null, ParcelMachine[].class).getBody();
        if (response == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(response)
//                .filter(product -> product.getRating().getRate() > 3)
                .toList();
    }

    public String makePayment(double sum) {
        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";
        RestTemplate restTemplate = new RestTemplate();

        EveryPayBody everyPayBody = new EveryPayBody();
        everyPayBody.setAmount(sum);
        everyPayBody.setAccount_name("EUR3D1");
        everyPayBody.setTimestamp(ZonedDateTime.now().toString());
        Random random = new Random();
        everyPayBody.setOrder_reference("ab" + random.nextInt(99999));
        everyPayBody.setCustomer_url("https://www.omniva.ee/locations.json");
        everyPayBody.setNonce(ZonedDateTime.now().toString() + random.nextInt(Integer.MAX_VALUE));
        everyPayBody.setApi_username("e36eb40f5ec87fa2");

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("e36eb40f5ec87fa2", "7b91a3b9e1b74524c2e9fc282f8ac8cd");

        HttpEntity<EveryPayBody> entity = new HttpEntity<>(everyPayBody,  headers);

        EveryPayResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, EveryPayResponse.class).getBody();
        if (response == null) {
            return "";
        }
        return response.getPayment_link();
    }
}
