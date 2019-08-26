package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.CompanyEntity;

public interface CompanyJPARepo extends JpaRepository<CompanyEntity, Integer>{

}
