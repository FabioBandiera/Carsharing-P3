package it.demo.carsharing.chainofres;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.ListaPrenotazioni;
import it.demo.carsharing.repository.ListaPrenotazioniRepository;

public class SceltaAuto2 extends GestionePrenotazione{	
	//INFINE L'AUTO CHE SI TROVA SICURAMENTE NEL PARCHEGGIO VIENE ANALIZZATA PER CAPIRE SE HA UNA PRENOTAZIONE PENDENTE.
	//SE L'AUTO NON NE HA VERRA' RITORNATA LA SUA TARGA.
	
	@Autowired 
	ListaPrenotazioniRepository listaPrenotazioniRepository;


@Override
	public String sceltaTarga(Car carMaybe, Collection<ListaPrenotazioni> prens, String idPark)	{
		for(ListaPrenotazioni prenotazione: prens) {
			if(prenotazione.getTarga_auto().equals(carMaybe.getTarga())) {
				return "Quest'auto non è disponibile.";
			} 
		}
		return "ok it's it";
	}
}