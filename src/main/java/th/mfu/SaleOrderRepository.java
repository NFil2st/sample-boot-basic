package th.mfu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {
    List<SaleOrder> findAll();
    List<SaleOrder> findByCustomer_id(Integer id);
}

