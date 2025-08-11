package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductManager controller;

    @Test
    public void createAndSeacrh() {
        Product product = new Product();
        product.setName("Dummy Dummy");
        product.setPrice(10.2);
        product.setDescription("test 123");
        ResponseEntity<String> response = controller.createProduct(product);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseEntity<Collection> getResponse = controller.getCustomersByDis("test");
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    }

    @Test
    public void createAndDelete() {
        // Get the initial number of products
        ResponseEntity<Collection> initialGetResponse = controller.getAllProduct();
        assertEquals(HttpStatus.OK, initialGetResponse.getStatusCode());
        int initialSize = initialGetResponse.getBody().size();

        // Create a new product to be deleted
        Product product = new Product();
        product.setName("Delete Me Product");
        product.setPrice(50.0);
        product.setDescription("Product to delete");

        ResponseEntity<String> createResponse = controller.createProduct(product);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        // Verify the product was created
        ResponseEntity<Collection> getResponseAfterCreate = controller.getAllProduct();
        assertEquals(HttpStatus.OK, getResponseAfterCreate.getStatusCode());
        assertEquals(initialSize + 1, getResponseAfterCreate.getBody().size());

        // Find the newly created product to get its ID
        List<Product> productsAfterCreate = (List<Product>) getResponseAfterCreate.getBody();
        Product newProduct = productsAfterCreate.stream()
                .filter(p -> "Delete Me Product".equals(p.getName()))
                .findFirst()
                .orElse(null);
        assertNotNull(newProduct, "Newly created product should not be null");
        Integer productId = newProduct.getId();

        // Delete the product
        ResponseEntity<String> deleteResponse = controller.deleteProduct(productId);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        // Verify the product was deleted
        ResponseEntity<Collection> getResponseAfterDelete = controller.getAllProduct();
        assertEquals(HttpStatus.OK, getResponseAfterDelete.getStatusCode());
        assertEquals(initialSize, getResponseAfterDelete.getBody().size());
    }
}
