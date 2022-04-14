package it.demo.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.demo.carsharing.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

	
}
