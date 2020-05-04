package br.com.mega.hack.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

	List<Establishment> findByAddressNeighborhood(String neighborhood);

	List<Establishment> findByUserId(Long user);
	
	Establishment findOneById(Long user);
	
	Establishment findByUserUuid(String user);
	
	List<Establishment> findByUserUuidIn(List<String> user, Pageable pageable);
}
