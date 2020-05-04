package br.com.mega.hack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.Order;
import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.ProductProjection;
import br.com.mega.hack.model.enums.StatusOrder;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("FROM Order o JOIN o.user u WHERE u.id= :id")
	List<Order> findListByUser(@Param("id") Long id);
	
	@Query("FROM Order o JOIN o.establishment e WHERE e.id= :id")
	List<Order> findListByEstablishment(@Param("id")Long  id);
	
	@Query("FROM Order o JOIN o.user u WHERE u.id= :id AND o.status = :status")
	List<Order> findListByUserAndStatus(@Param("id") Long id, @Param("status") StatusOrder status);
	
	@Query("FROM Order o JOIN o.establishment e WHERE e.id= :id AND o.status = :status")
	List<Order> findListByEstablishmentAndStatus(@Param("id")Long  id, @Param("status") StatusOrder status);
	
	List<Order> findByStatus(StatusOrder statusOrder);
	
	@Query("SELECT o FROM DeliveryOrder do JOIN do.order o JOIN do.deliveryman dm WHERE dm.id = :id AND o.status = :status")
	List<Order> findByDeliveryAndStatus(@Param("id") Long id, @Param("status") StatusOrder status);

	Optional<Order> findById(Long id);
}
