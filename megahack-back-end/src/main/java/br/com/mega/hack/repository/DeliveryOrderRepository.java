package br.com.mega.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.DeliveryOrder;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

	@Query("FROM DeliveryOrder do JOIN do.order o WHERE o.id = :idOrder")
	DeliveryOrder findByOrder(@Param("idOrder") Long id);
}
