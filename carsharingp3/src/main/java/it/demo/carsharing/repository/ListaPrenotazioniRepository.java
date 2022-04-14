package it.demo.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.demo.carsharing.model.ListaPrenotazioni;

@Repository
public interface ListaPrenotazioniRepository extends JpaRepository<ListaPrenotazioni, String> {

}
