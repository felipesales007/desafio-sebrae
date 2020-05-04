package br.com.mega.hack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long>{

	Boolean existsByEmail(String value);
	
	@Query("select u from UserApp u where u.email = :email")
	Optional<UserApp> findByEmail(String email);
	
	UserApp findOneById(Long userId);
	
	UserApp findOneByUuid(String uuid);
	
	@Query("select u from UserApp u where u.uuid in :uuids")
	List<UserApp> findByUUIDIn(List<String> uuids, Pageable pageable);

}
