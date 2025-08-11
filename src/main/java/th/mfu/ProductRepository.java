package th.mfu;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByDescriptionStartingWith(String prefix);
    List<Product> findAllByOrderByPriceAsc();
}