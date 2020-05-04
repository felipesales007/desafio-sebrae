package br.com.mega.hack.repository;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.Order;
import br.com.mega.hack.model.OrderItems;
import br.com.mega.hack.model.dto.ProductProjection;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

	List<OrderItems> findByOrder(Order order);

	List<OrderItems> findByOrderId(Long id);

	@Query("select sum(p.price)+5.99 as value from OrderItems oi join oi.product p join oi.order o " + "  where o.id = :id  ")
	ProductProjection getValue(Long id);
}
