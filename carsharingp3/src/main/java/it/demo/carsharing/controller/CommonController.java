package it.demo.carsharing.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.demo.carsharing.model.Car;
import it.demo.carsharing.model.ListaPrenotazioni;
import it.demo.carsharing.model.Parcheggio;
import it.demo.carsharing.model.Persona;
import it.demo.carsharing.services.CarService;
import it.demo.carsharing.services.ListaPrenotazioniService;
import it.demo.carsharing.services.ParcheggioService;
import it.demo.carsharing.services.PersonaService;

//QUESTE ANNOTATION SERVONO A SPRING ED HIBERNATE PER GESTIRE LA CREAZIONE E UPDATE DEL DB SUL TOMCAT.

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/controller")
public class CommonController {
	
	//QUESTO E' IL CONTROLLER COMUNE. TUTTI I METODI VERRANNO CHIAMATI DA QUI, MA SARANNO IMPLEMENTATI NELLE INTERFACCE
	//E NEI SERVICEIMPL RELATIVI AI VARI MODELLI.
	
	
	@Autowired
	CarService carService;

	@Autowired
	ParcheggioService parcheggioService;
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	ListaPrenotazioniService listaPrenotazioniService;

	// METODI PER LA PARTE DELL'EROGATORE:
	
	//AGGIUNGI AUTO.

	// OGNI AUTO AGGIUNTA SOTTRAE 1300 EURO AL BUDGET DELL'EROGATORE, INOLTRE SI PUò SCEGLIERE
	//IL PARCHEGGIO IN CUI FAR RECAPITARE L'AUTO. LE ECCEZIONI RENDONO ROBUSTO QUESTO METODO (VEDERE CARSERVICEIMPL).
	@PostMapping("/addNewCar/{codiceFiscale}/{idParcheggio}")
	public String addNewCar(@RequestBody Car newCar, @PathVariable String codiceFiscale, @PathVariable String idParcheggio) throws Exception {
		return carService.addNewCar(newCar, codiceFiscale, idParcheggio);
	}
	
	//RIDISTRIBUIRE LE AUTO NEI PARCHEGGI.
	//I SEGUENTI METODI PERMETTONO DI RIDISTRIBUIRE LE AUTO NEI PARCHEGGI:
	
	//TROVA LE AUTO NEI PARCHEGGI. SERVE A PASSARE INFO PER LA TABELLA IN ANGULAR.
	@GetMapping("/getCarsFromNomeParcheggio/{nome_parcheggio}")
	public Collection<String> getCarsFromNomeParcheggio(@PathVariable String nome_parcheggio) {
		return parcheggioService.getCarsFromNomeParcheggio(nome_parcheggio);
	}
	//SPOSTA UN'AUTO DA UN PARCHEGGIO AD UNO SCELTO DALL'EROGATORE.
	@PutMapping("/spostaCarInPark/{nome_parcheggio}")
	public String spostaCarInPark(@RequestBody Car car, @PathVariable String nome_parcheggio) throws Exception {
		return parcheggioService.spostaCarInPark(car, nome_parcheggio);
	}
		
	//VISUALIZZARE LE INFO SUGLI UTENTI CHE NON RICONSEGNANO L'AUTO NEI TEMPI STABILITI E CATTIVI PAGATORI
				
	//QUESTO METODO RITORNA UNA COLLECTION DI PERSONE CHE ANCORA NON HANNO RICONSEGNATO L'AUTO IN TEMPO.
	@GetMapping("/utentiNotDoneRiconsegna")
	public Collection<Persona> utentiNotDoneRiconsegna(){
		return personaService.utentiNotDoneRiconsegna();
	}				
		
	
	//METODI PER LA PARTE DELL'UTENTE:	
		
	//QUESTA CHIAMATA CRUD AL DB GESTISCE LA REGISTRAZIONE AL SERVIZIO. ESSA VIENE CHIAMATA DA ANGULAR NEL COMPONENT
	//DI REGISTERUTENTE. TRAMITE QUESTA E' POSSIBILE INVIARE IL FORM CHE E' VISIBILE NELLA WEBAPP.
	//E' POSSIBILE ISCRIVERE ANCHE UN NUOVO EROGATORE.
	@PostMapping("/subscribeToCarsharing")
	public String subscribeToCarsharing(@RequestBody Persona persona) throws Exception {
		return personaService.subscribeToCarsharing(persona);
	}
	
	//QUESTO METODO CREA UNA PRENOTAZIONE. LA SCELTA DELLA TARGA è MODULATA DA UN CHAIN OF RESPONSIBILITY,COSI
	//DA RIPORTARE LA TARGA, SE NON OCCUPATA, DELL'AUTO CHE IL CLIENTE AVRà IN NOLEGGIO DAL PARCHEGGIO RICHIESTO.
	@PostMapping("/createNewPrenotazione/{nome_parcheggio}")
	public String createNewPrenotazione(@RequestBody ListaPrenotazioni prenotazione, @PathVariable String nome_parcheggio) {
		return listaPrenotazioniService.createNewPrenotazione(prenotazione, nome_parcheggio);
	}
	
	// QUESTO METODO GESTISCE LA RESTITUZIONE DI UN'AUTO OLTRE CHE IL PAGAMENTO DI CIO' CHE E' DOVUTO AL SERVIZIO.
	//NB: QUESTA CHAMATA RICHIEDE UN BODY DI TIPO PARCHEGGIO, IN REALTà è VUOTO POICHè ANGULAR RICHIEDE ESPLICITAMENTE
	//UN BODY PER EFFETTUARE UNA CHIAMATA AD UNA PUT, QUINDI PER BYPASSARE L'UNICO MODO è DARGLI IN PASTO UN BODY VUOTO COSI 
	//DA POTER FARE LO STESSO LA REST API, MA SENZA AFFLIGGERE IN NESSUN MODO I DATI , INFATTI NON VIENE CONTEMPLATO NEL SERVICEIMPL.
	@PutMapping("/returnCar/{codiceFiscale}/{idPark}/{bancomatOCarta}")
	public String returnCar(@RequestBody Parcheggio park, @PathVariable String codiceFiscale, @PathVariable String idPark, @PathVariable String bancomatOCarta) {
		return parcheggioService.returnCar(codiceFiscale, idPark, bancomatOCarta);
	}


	//METODI MISCELLANEI O DI USO NON RICHIESTO NELLA TRACCIA, USATI PRINCIPALMENTE PER GESTIRE ANGULAR:

	//TROVA DAL CODICE FISCALE LA TIPOLOGIA DELLA PERSONA. SERVE PER GLI ACCESSI.
	@GetMapping("/findTipologiaPersonaByCodiceFiscale/{codiceFiscale}")
	public String findTipologiaPersonaByCodiceFiscale(@PathVariable String codiceFiscale) {
		return personaService.findTipologiaPersonaByCodiceFiscale(codiceFiscale);
	}

	//TROVA LA MACCHINA TRAMITE LA TARGA NEL DB.
	@GetMapping("/findCarByTarga/{targa}")
	public Optional<Car> findCarByTarga(@PathVariable String targa) {
		return carService.findCarByTarga(targa);
	}
	
	//TROVA LA PERSONA TRAMITE IL CODICE FISCALE.
	@GetMapping("/findPersonaByCodiceFiscale/{codiceFiscale}")
	public Persona findPersonaByCodiceFiscale(@PathVariable String codiceFiscale) {
		return personaService.getPersonaByCodiceFiscale(codiceFiscale);
	}

	//TROVA PIù AUTO TRAMITE UN ID PARCHEGGIO.
	@GetMapping("/findAllCarsByParkId/{idParcheggio}")
	public Collection<Car> findAllCarsByParkId(@PathVariable String idParcheggio){
			return carService.findAllCarsByParkId(idParcheggio);
	}
	
	//QUESTO METODO CONTROLLA I PRIVILEGI DI UNA PERSONA NEL DB
	@GetMapping("/personaIsErogatore/{codiceFiscale}")
	public String personaIsErogatore(@PathVariable String codiceFiscale) {
		return personaService.personaIsErogatore(codiceFiscale);
	}
	
	//TROVA TUTTI I PARCHEGGI.
	@GetMapping("/findAllParks")
	public Collection<Parcheggio> findAllParks(){
		return parcheggioService.findAllParks();
	}
	
	//TROVA TUTTE LE AUTO
	@GetMapping("/findAllCars")
	public Collection<Car> findAllCars(){
		return carService.findAllCars();
	}
	
	//TROVA LA PERSONA DAL CODICE FISCALE.
	@GetMapping("/getPersonaByCodiceFiscale/{codiceFiscale}")
	public Persona getPersonaByCodiceFiscale(@PathVariable String codiceFiscale) {
		return personaService.getPersonaByCodiceFiscale(codiceFiscale);
	}		
		
	//QUESTO METODO CONTA LE AUTO PRESENTI IN UN PACHEGGIO.
	@GetMapping("/getNumCarsByParkId/{idParcheggio}")
	public Integer getNumCarsByParkId(@PathVariable String idParcheggio) {
		return carService.getNumCarsByParkId(idParcheggio);
	}
	
}