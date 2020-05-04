package br.com.mega.hack.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

	List<Chat> findAllByFrom(String to);
	
	List<Chat> findAllByFromOrTo(String to, String from);
	
	@Query("select c from Chat c where (c.to = :to and c.from = :from) or (c.to = :from and c.from = :to) ")
	List<Chat> findConversation(String to, String from);
	
	@Query("select c from Chat c where c.id in (select max(c2.id) FROM Chat c2 where "
			+ "c2.to =:to group by c2.to, c2.from)")
	Set<Chat> findForLatestContactsTo(String to);
	
	@Query("select c from Chat c where c.id in (select max(c2.id) FROM Chat c2 where "
			+ "c2.from =:from group by c2.to, c2.from)")
	Set<Chat> findForLatestContactsFrom(String from);
}
