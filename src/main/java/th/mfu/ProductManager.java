package th.mfu;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductManager {

    @Autowired
    ProductRepository productRepo;

    @GetMapping("/products")
    public ResponseEntity<Collection> getAllProduct() {
        return new ResponseEntity<Collection>(productRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productRepo.save(product);
        return new ResponseEntity<String>("Product created", HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return new ResponseEntity<String>("Product deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        if (productRepo.existsById(id)) {
            Optional<Product> product = productRepo.findById(id);
            return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/product/{prefix}")
    public ResponseEntity<Collection> getCustomersByDis(@PathVariable String prefix) {
        List<Product> products = productRepo.findByDescriptionStartingWith(prefix);
        return new ResponseEntity<Collection>(products, HttpStatus.OK);
    }

    @GetMapping("/products/price-list")
    public ResponseEntity<Collection> getPriceProduct() {
        List<Product> products = productRepo.findAllByOrderByPriceAsc();
        return new ResponseEntity<Collection>(products, HttpStatus.OK);
    }
}