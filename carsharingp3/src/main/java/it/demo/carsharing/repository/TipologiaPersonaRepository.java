package it.demo.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.demo.carsharing.model.TipologiaPersona;

@Repository
public interface TipologiaPersonaRepository extends JpaRepository<TipologiaPersona, String> {

}
