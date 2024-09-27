package es.demobet.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.demobet.users.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
