package th.mfu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductReviewController {

    @Autowired
    private ProductReviewRepository reviewRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/reviews")
    public ResponseEntity<List<ProductReview>> getAllReviews() {
        return new ResponseEntity<>(reviewRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody ProductReview review) {
        if (review.getCustomer() == null || review.getCustomer().getId() == null) {
            return new ResponseEntity<>("Customer info missing", HttpStatus.BAD_REQUEST);
        }
        if (review.getProduct() == null || review.getProduct().getId() == null) {
            return new ResponseEntity<>("Product info missing", HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> customerOpt = customerRepo.findById(review.getCustomer().getId());
        if (!customerOpt.isPresent()) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        Optional<Product> productOpt = productRepo.findById(review.getProduct().getId());
        if (!productOpt.isPresent()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        review.setCustomer(customerOpt.get());
        review.setProduct(productOpt.get());

        reviewRepo.save(review);

        return new ResponseEntity<>("Review created", HttpStatus.CREATED);
    }
}
