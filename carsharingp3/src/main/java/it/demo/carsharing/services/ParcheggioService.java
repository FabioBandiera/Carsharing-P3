package it.demo.carsharing.services;

import java.util.Collection;

import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.Parcheggio;

public interface ParcheggioService {
	
	public Collection<String> getCarsFromNomeParcheggio(String nome_parcheggio);
	
	public String spostaCarInPark(Car car, String nome_parcheggio) throws Exception;

	public Collection<Parcheggio> findAllParks();

	public String returnCar(String codiceFiscale, String idPark, String bancomatOCarta);

}
