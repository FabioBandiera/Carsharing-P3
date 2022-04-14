package it.demo.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.demo.carsharing.model.Parcheggio;

@Repository
public interface ParcheggioRepository extends JpaRepository<Parcheggio, String> {
	
}
