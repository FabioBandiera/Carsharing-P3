package it.demo.carsharing.chainofres;

import java.util.Collection;

import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.ListaPrenotazioni;

public abstract class GestionePrenotazione {
	//IMPLEMENTAZIONE DEL CHAIN OF RESPONSIBILITY PER CREARE UNA PRENOTAZIONE, CLASSE ASTRATTA PER CREARE 
	//LE DUE CLASSI PER LA SCELTA DELL'AUTO.
	
	protected GestionePrenotazione successore;
	
	public void setSuccessore(GestionePrenotazione successor) {
		successore = successor;
	}
	public abstract String sceltaTarga (Car carMaybe, Collection<ListaPrenotazioni> prens, String idPark);

}
