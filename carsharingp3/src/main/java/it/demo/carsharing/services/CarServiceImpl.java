package it.demo.carsharing.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.Parcheggio;
import it.demo.carsharing.model.Persona;
import it.demo.carsharing.repository.CarRepository;
import it.demo.carsharing.repository.ParcheggioRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ParcheggioRepository parcheggioRepository;
	
	@Autowired
	PersonaService personaService;
	
	// QUESTO METODO AGGIUNGE UNA NUOVA AUTO E SOTTRAE AL BUDGET DELL'EROGATORE 1300 EURO, TROVANDONE IL BUDGET DAL CODICE FISCALE,
	// INOLTRE RICHIEDE L'ID DI UN PARCHEGGIO COSI DA POTER CONSEGNARE LI LA 500 APPENA "COMPRATA".
	@Override
	public String addNewCar(Car newCar, String codiceFiscale, String idParcheggio) throws Exception {
		Collection<Car> allCars = carRepository.findAll();
		int countCars = allCars.size();
		Collection<Parcheggio> allParcheggi =  parcheggioRepository.findAll();
		int countParks = allParcheggi.size();
		Collection<Car> thisParkCars = findAllCarsByParkId(idParcheggio);
		int thisParkCountCars = thisParkCars.size(); 
		try {
		if(countCars<=(countParks*50)) {
			if(thisParkCountCars<=50) {
		Persona persona = personaService.getPersonaByCodiceFiscale(codiceFiscale);
		if(personaService.existsById(codiceFiscale)) {
		if(!(persona.getBudget()<1300)) {
		int newBudget = persona.getBudget() - 1300;
		persona.setBudget(newBudget);
		newCar.setId_parcheggio(idParcheggio);
		newCar.setCfutente_associato(null);
		carRepository.save(newCar);
		return "Operazione completata con successo!";
		}else throw new Exception("Fondi esauriti!");
		}else throw new Exception("Questo codice fiscale non è presente nel database!");
			}else throw new Exception("Questo parcheggio è gia pieno");
		}
		else throw new Exception("Ci sono gia troppe macchine nel database!");
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//QUESTO METODO TROVA TUTTE LE AUTO CON UN DETERMINATO ID PARCHEGGIO.
	@Override
	public Collection<Car> findAllCarsByParkId(String idParcheggio){
		Collection<Car> allCarsInPark = new ArrayList<Car>();
		Collection<Car> allCars = carRepository.findAll();
		for(Car car: allCars) {
			if(car.getId_parcheggio()!=null) {
				if(car.getId_parcheggio().equals(idParcheggio)) {
					allCarsInPark.add(car);
				}
			}
		}
			return allCarsInPark;
	}

	//QUESTO METODO RITORNA TUTTE LE AUTO PRESENTI NEL DB.
	@Override
	public Collection<Car> findAllCars(){
		return carRepository.findAll();
	}
	
	//QUESTO METODO ESPONE IL SAVE DI CARREPOSITORY.
	@Override
	public void saveCar(Car car) {
		carRepository.save(car);
	}
	
	//QUESTO METODO TROVA UN'AUTO DALLA SUA TARGA
	@Override
	public Optional<Car> findCarByTarga(String targa) {
		return carRepository.findById(targa);
	}
	
	//QUESTO METODO TROVA IL NUMERO DI AUTO CONTENUTE IN UN PARCHEGGIO.
	@Override 
	public Integer getNumCarsByParkId(String idParcheggio) {
		Collection<Car> cars = carRepository.findAll();
		Collection<Car> finalc = new ArrayList<Car>();
		int counter = 0;
		for(Car cart: cars) {
			if( !(cart.getId_parcheggio() == null)) {
				finalc.add(cart);
			}
		}
		for(Car cartest: finalc) {
			if(cartest.getId_parcheggio().equals(idParcheggio)) {
				counter++;
			}
		}
		return counter;
	}
	
}
