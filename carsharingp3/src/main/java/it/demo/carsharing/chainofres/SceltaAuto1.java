package it.demo.carsharing.chainofres;

import java.util.Collection;

import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.ListaPrenotazioni;
									
public class SceltaAuto1 extends GestionePrenotazione{ 
	//IN QUESTA PRIMA SCELTA DELL'AUTO SI CERCA PRIMA DI DISTINGUERE LE AUTO APPARTENENTI AL PARCHEGGIO
	// COSI CHE LA SECONDA SCELTA AUTO POSSA TROVARNE UNA NON SOTTO PRENOTAZIONE.
	
	@Override
	public String sceltaTarga(Car carMaybe, Collection<ListaPrenotazioni> prens, String idPark) {
		if(carMaybe.getId_parcheggio() == null) {
			return "not in park";
		}
		if(carMaybe.getId_parcheggio().equals(idPark)) {
			return "ok in park";
		}
		return "not in park";
	}
}
