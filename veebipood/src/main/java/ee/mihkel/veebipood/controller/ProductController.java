package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// annotation --> mingi koodi sisse tõmbamine
//  on vaja alati importi
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    // Dependency Injection
    // Kui tekib ProductController, siis tekib automaatselt temaga seotult
    //      ka ProductRepository, mida ma saan tema sees kasutama hakata
    @Autowired
    private ProductRepository productRepository;

    // localhost:8080/products
    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // localhost:8080/products
    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("Cannot add with ID"); // kui tuleb exception, siis kood katkeb, siit edasi ei minda
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    // localhost:8080/products/1
    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete product which has been ordered");
        }
        return productRepository.findAll();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("Cannot edit without ID"); // kui tuleb exception, siis kood katkeb, siit edasi ei minda
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    // localhost:8080/products/1
    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PutMapping("insert-products")
    public List<Product> insertProduct(@RequestBody List<Product> products) {
        productRepository.saveAll(products);
        return productRepository.findAll();
    }

    @GetMapping("products-by-category")
    public List<Product> productsByCategory(@RequestParam Long id) {
        return productRepository.findByCategory_Id(id);
    }

}

// Git

// Teenusepakkujad koodi üles laadimiseks:
// Github
// Bitbucket
// Gitlab

// Miks peab koodi üles laadima
// 1. koodi taastamine viimasest Githubi seisust
// ---> Githubi peab minema ainult töötav kood
// 2. portfoolio
// 3. ühine koodi kallal töötamine
// 4. kui arvuti kaob, siis saab koodi taastada


// Staatuskood:
// 1xx informatsiooniline (harva)
// 2xx EDUKAS PÄRING
// 3xx suunamine (harva)
// 4xx FRONT-END VIGA   400-üldine  404-pole olemas  405-vale method (GET/PUT)  415-ei saadeta JSON kuju
// 5xx BACK-END VIGA   500-üldine  index on listist väljaspool, andmebaas on maas