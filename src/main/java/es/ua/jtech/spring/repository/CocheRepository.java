package es.ua.jtech.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ua.jtech.spring.modelo.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, String> {

}
