package it.demo.carsharing.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.Parcheggio;
import it.demo.carsharing.model.Persona;
import it.demo.carsharing.repository.CarRepository;
import it.demo.carsharing.repository.ParcheggioRepository;
import it.demo.carsharing.repository.PersonaRepository;
import it.demo.carsharing.strategy.PagamentoConBancomat;
import it.demo.carsharing.strategy.PagamentoConCarta;

@Service
public class ParcheggioServiceImpl implements ParcheggioService {
	
	@Autowired
	ParcheggioRepository parcheggioRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	PersonaRepository personaRepository;
	
	//QUESTO METODO RITORNA SEMPLICEMENTE TUTTE LE TARGHE D'AUTO PRESENTI IN UN PARCHEGGIO
	//SERVE ALLA STAMPA DELLA TABLE IN ANGULAR
	public Collection<String> getCarsFromNomeParcheggio(String nome_parcheggio) {
		Collection<Car> carsInThisPark = carService.findAllCars();
		Collection<String> carsInThisParkRet = new ArrayList<String>();
		Collection<Parcheggio> parcheggio = parcheggioRepository.findAll();
		for (Parcheggio park : parcheggio) {
			if (park.getNome_parcheggio().equals(nome_parcheggio)) {
				for (Car car : carsInThisPark) {
					if (!(car.getId_parcheggio() == null) && car.getId_parcheggio().equals(park.getIdParcheggio())) {
						carsInThisParkRet.add(car.getTarga());
					}
				}
			}
		}
		return carsInThisParkRet;
	}

	//QUESTO METODO CONSENTE LO SPOSTAMENTO DI UN'AUTO DA UN PARCHEGGIO AD UN'ALTRO,(PUNTO DELLA RIDISTRIBUZIONE)
			public String spostaCarInPark(Car car, String nome_parcheggio) throws Exception{
				Collection<Parcheggio> parcheggio = parcheggioRepository.findAll();
				try {
				for (Parcheggio park : parcheggio) {
					if (!(park.getNome_parcheggio().equals(nome_parcheggio))) {
						continue;
					}
					if(car.getId_parcheggio().equals(park.getIdParcheggio())) {
						throw new Exception("Auto gia in questo parcheggio.");
					}
						car.setId_parcheggio(park.getIdParcheggio());
						carService.saveCar(car);
						return "Operazione effettuata con successo!";
				 }
				} 
				catch (Exception e) {
					return e.getMessage();
				}
				return "Il parcheggio non è stato trovato. Ricontrollare il nome scritto.";
			}
	
	//SI TROVANO TUTTI I PARCHEGGI.
	@Override
	public Collection<Parcheggio> findAllParks(){
		return parcheggioRepository.findAll();		
	}

	
	//QUESTO METODO GESTISCE LA RICONSEGNA DELLE AUTO ED IL PAGAMENTO.
	//IL METODO è DIVISO IN 2 PARTI: LA CHIAMATA ALLE UTILITY DELLO STRATEGY PER IL PAGAMENTO E LA PREPARAZIONE DEL
	//NECESSARIO PER PERMETTERE TALE CALCOLO, ED UNA SECONDA PARTE IN CUI SI DISASSOCIANO CLIENTE ED AUTO
	//OLTRE CHE IN CUI SI SALVANO TALI CAMBIAMENTI COME IL NUOVO BUDGET POST PAGAMENTO. (PUNTO DELLA RICONSEGNA E DEL PAGAMENTO).
	public String returnCar(String codiceFiscale, String idPark, String bancomatOCarta) {
		@SuppressWarnings("unused")
		String ret = "";
		int total = 0;		
		Persona persona = personaRepository.getById(codiceFiscale);
		try {
		if(persona.getTargaCarAssociata() == null || persona.getTargaCarAssociata().equals("null") || persona.getTargaCarAssociata().equals("Null")) {
			throw new Exception("Questo utente non ha una macchina in custodia.");
		} 
		if(persona.getData_fissata_restituzione() == null) {
			throw new Exception("Non c'è stata nessuna prenotazione per questo cliente.");
		}
		LocalDate data_riconsegnaLD = persona.getData_fissata_restituzione().toLocalDate();
		LocalDate data_inizio_usoLD = persona.getDataAssociazioneCar().toLocalDate();
		Date data_riconsegna = persona.getData_fissata_restituzione();
		//QUI SI EFFETTUANO LE CHIAMATE ALLE CLASSI DELLO STRATEGY, LE QUALI RITORNERANNO IL TOTALE DA SOTTRARRE AL BUSGET.
		if(bancomatOCarta.equals("bancomat") || bancomatOCarta.equals("Bancomat")) {
			PagamentoConBancomat paybanco = new PagamentoConBancomat();
			total = paybanco.pagaCon(data_riconsegna, data_riconsegnaLD, data_inizio_usoLD);
		} else if(bancomatOCarta.equals("Carta") || bancomatOCarta.equals("carta")) {
			PagamentoConCarta paycarta = new PagamentoConCarta();
			total = paycarta.pagaCon(data_riconsegna, data_riconsegnaLD, data_inizio_usoLD);
		} else throw new Exception("Metodo di pagamento non valido, controlla la scrittura.");
		persona.setBudget(persona.getBudget() - total);
		if(persona.getBudget() < 0) {
			return "Non hai abbastanza fondi per pagare. Si prega di ricaricare " + bancomatOCarta;
		}		
		
		//QUI VIENE PRESA L'AUTO E "SPOSTATA" NEL PARCHEGGIO DESIDERATO DALL'UTENTE.
		Car car = carRepository.getById(persona.getTargaCarAssociata());
		persona.setTargaCarAssociata(null);
		persona.setData_fissata_restituzione(null);
		persona.setDataAssociazioneCar(null);
		personaRepository.save(persona);
		car.setCfutente_associato(null);
		car.setId_parcheggio(idPark);
		carRepository.save(car);
		return ret = "Operazione effettuata con successo! L'auto ora si trova nel parcheggio con ID " + car.getId_parcheggio() + ", hai pagato con " + bancomatOCarta + " per un totale di euro " + total;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
}
