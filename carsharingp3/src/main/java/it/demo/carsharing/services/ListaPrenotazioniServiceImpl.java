package it.demo.carsharing.services;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.demo.carsharing.chainofres.GestionePrenotazione;
import it.demo.carsharing.chainofres.SceltaAuto1;
import it.demo.carsharing.chainofres.SceltaAuto2;
import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.ListaPrenotazioni;
import it.demo.carsharing.model.Parcheggio;
import it.demo.carsharing.repository.CarRepository;
import it.demo.carsharing.repository.ListaPrenotazioniRepository;
import it.demo.carsharing.repository.ParcheggioRepository;

@Service
public class ListaPrenotazioniServiceImpl implements ListaPrenotazioniService {
	
	@Autowired
	ListaPrenotazioniRepository listaPrenotazioniRepository;
	
	@Autowired
	ParcheggioRepository parcheggioRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	PersonaService personaService;
	
	
	//Metodo che crea una prenotazione
	//Da front end serve inviare una prenotazione , viene trovata un'auto disponibile, presente nel parcheggio e non prenotata,
	//sceltaAuto1 identifica il parcheggio richiesto, sceltaAuto2 un'auto disponibile.
	@Override
	public String createNewPrenotazione(ListaPrenotazioni prenotazione, String nome_parcheggio) {
		String ret = "";
		GestionePrenotazione sceltaAuto = new SceltaAuto1();
		GestionePrenotazione sceltAuto2 = new SceltaAuto2();
		
		Collection<ListaPrenotazioni> prens = listaPrenotazioniRepository.findAll();
		Collection<Parcheggio> parklist = parcheggioRepository.findAll();
		Collection<Car> allCars = carRepository.findAll();
		String idpark = "";
		Date tdate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {	
			if(!(personaService.personaIsErogatore(prenotazione.getCf_utente()) == "true")) {
				if(!prenotazione.getData_inizio_prenotazione().before(formatter.parse(formatter.format(tdate)))){
					for(Parcheggio park: parklist) {
						if(park.getNome_parcheggio().equals(nome_parcheggio)) {
							idpark = park.getIdParcheggio();
							if(idpark.equals("")) {
								throw new Exception("Il parcheggio non è stato trovato, controlla di aver scritto bene il nome.");
							}
							break;
						} 
					}
					for(Car carMaybe: allCars) {
						ret = sceltaAuto.sceltaTarga(carMaybe, prens, idpark);
						if(ret.equals("ok in park")) {
							String result = sceltAuto2.sceltaTarga(carMaybe, prens, idpark);
							if(result.equals("ok it's it")) {
								ret = carMaybe.getTarga();
								break;
							}
						} 
						
					}
					if(ret.equals("Quest'auto non è disponibile.")) {
						throw new Exception("Nessuna auto da poter prenotare.");
					} else if(ret.equals("not in park")) {
						throw new Exception("Nessuna auto disponibile da questo parcheggio.");
					}
					prenotazione.setTarga_auto(ret); 
					Integer tenner = (int) ((Math.random() * (9999999 - 1)) + 0);
					String ticket = Integer.toString(tenner);
					prenotazione.setTicket(ticket);
					prenotazione.setId_parcheggio_prestabilito(idpark);
					listaPrenotazioniRepository.save(prenotazione);
					return "Prenotazione effettuata! Avrai l'auto con targa: " + ret;	
				} else throw new Exception("Non puoi tornare indietro nel tempo!");
			} else throw new Exception("Gli erogatori non possono noleggiare.");
		}
		catch(Exception e) {
			 ret = e.getMessage();
			 return ret;
		}		
		
	}

}
