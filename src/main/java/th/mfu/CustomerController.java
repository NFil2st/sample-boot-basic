package th.mfu;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    private CustomerTierRepository customerTierRepo;


    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers() {
        return new ResponseEntity<Collection>(customerRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        if (customer.getCustomerTier() != null && customer.getCustomerTier().getId() != null) {
            Optional<CustomerTier> tierOptional =  customerTierRepo.findById(customer.getCustomerTier().getId());
            if (tierOptional.isPresent()) {
                customer.setCustomerTier(tierOptional.get());
            } else {
                return new ResponseEntity<String>("Customer tier not found", HttpStatus.NOT_FOUND);
            }
        }
        customerRepo.save(customer);
        return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id){
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return new ResponseEntity<String>("Customer deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomers(@PathVariable Integer id) {
        if (customerRepo.existsById(id)) {
            Optional<Customer> customer = customerRepo.findById(id);
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/name/{prefix}")
    public ResponseEntity<Collection> getCustomersByName(@PathVariable String prefix) {
        List<Customer> customers = customerRepo.findByNameStartingWith(prefix);
        return new ResponseEntity<Collection>(customers, HttpStatus.OK);
    }

}