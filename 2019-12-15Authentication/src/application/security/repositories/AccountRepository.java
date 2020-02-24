package application.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.security.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
