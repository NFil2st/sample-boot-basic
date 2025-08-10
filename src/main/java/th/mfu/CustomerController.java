package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
    private int nextid =1;

    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers() {
        return new ResponseEntity<Collection>(customers.values(), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        customer.setId(nextid);
        customers.put(nextid, customer);
        nextid++;
        return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id){
        if (customers.containsKey(id)) {
            customers.remove(id);
            return new ResponseEntity<String>("Customer deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomers(@PathVariable Integer id) {
        if (customers.containsKey(id)) {
            return new ResponseEntity<Customer>(customers.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }
}