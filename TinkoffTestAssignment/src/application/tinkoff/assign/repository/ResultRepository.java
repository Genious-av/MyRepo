package application.tinkoff.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.tinkoff.assign.entity.ResultEntity;


public interface ResultRepository extends JpaRepository<ResultEntity, Integer>{

}
