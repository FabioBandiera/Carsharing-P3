package it.demo.carsharing.services;

import java.util.Collection;
import java.util.Optional;

import it.demo.carsharing.model.Car;

public interface CarService {
	//I SERVICE NON SONO ALTRO CHE INTERFACCE NELLE QUALI DEFINIAMO I METODI CHE IMPLEMENTEREMO NEI 
	//SERVICEIMPL, COME DA NOME, COSI DA POTERLI RICHIAMARE NEL CONTROLLER CHIAMANDO UN SERVICE DA QUESTE INTERFACCE.
	//E' UN PROCESSO CON IL QUALE OGNI MODEL AVRà DEI SERVIZI CHE PUò PREDISPORRE.
	
	public String addNewCar(Car newCar, String codiceFiscale, String idParcheggio) throws Exception;
	
	public Collection<Car> findAllCarsByParkId(String idParcheggio);

	public Collection<Car> findAllCars();

	public void saveCar(Car car);

	public Optional<Car> findCarByTarga(String targa);

	public Integer getNumCarsByParkId(String idParcheggio);
	
}
