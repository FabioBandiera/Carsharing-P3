package it.demo.carsharing.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import it.demo.carsharing.model.Persona;
import it.demo.carsharing.model.TipologiaPersona;
import it.demo.carsharing.repository.PersonaRepository;
import it.demo.carsharing.repository.TipologiaPersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService{
	
	//DI SEGUITO LE IMPLEMENTAZIONI DI PERSONA SERVICE, METODI CHE AGISCONO SULLE PERSONE.
	//ESSENDO PERSONA E TIPOLOGIA PERSONA DIPENDENTI UNO DALL'ALTRO, IN QUESTO SERVICEIMPL USERò ANCHE 
	//TIPOLOGIA PERSONA REPOSITORY, COSI DA NON OCCUPARE ULTERIORE SPAZIO CON UN'ALTRO INUTILE SERVICE.
	
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	TipologiaPersonaRepository tipologiaPersonaRepository;
	
	//(PUNTO DELLA REGISTRAZIONE) DA QUESTA CHIAMATA POSSIAMO REGISTRARCI SIA COME UTENTI CHE EROGATORI.
	@Override
	public String subscribeToCarsharing(@RequestBody Persona persona) throws Exception {
		Date tdate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if(formatter.parse(formatter.format(persona.getDataIscrizione())).equals(formatter.parse(formatter.format(tdate)))) {
				if(persona.getTipologiaPersona().equals("1")) {
					if(persona.getBudget()<=14999) {
						throw new Exception("Non puoi iscriverti come Erogatore con un budget minore di 15000.");
					}
				}
				if(persona.getTipologiaPersona().equals("2")) {
					if(persona.getBudget()<=300) {
						throw new Exception("Non puoi iscriverti come Utente con un budget minore di 300.");
					}
					persona.setBudget(persona.getBudget()-50);
				}
			} else throw new Exception("La data non corrisponde a quella odierna."); 
			personaRepository.save(persona);
			return "Registrazione effettuata con successo! Ora Accedi.";
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//QUESTO METODO CHECKA SE LA TIPOLOGIA DI PERSONA è 1=EROGATORE O 2=UTENTE.
	@Override
		public String personaIsErogatore(String codiceFiscale) {
		Persona persona = personaRepository.getById(codiceFiscale);
		try {
			if(persona.getTipologiaPersona().equals("1")) {
				return "true" ;
			} 
			return "false";			
		}
		catch (Exception e) {
			return e.getMessage();
		}
		}
	
	//Questo metodo trova la tipologia persona con in input il codice fiscale. Inoltre controlla il pagamento della tassa
	//annuale nel caso si parli di un utente che non paga da un anno tale tassa.
	@Override
	public String findTipologiaPersonaByCodiceFiscale(String codiceFiscale) {	
	String finale ="";
		Persona persona = personaRepository.getById(codiceFiscale);
		TipologiaPersona tipologiaPersona = tipologiaPersonaRepository.getById(persona.getTipologiaPersona());
		Date tdate = new Date(System.currentTimeMillis());
		try {
			if(persona.getTipologiaPersona().equals("1")) {
				return "true" ;
			} 
			if(persona.getTipologiaPersona().equals("2")){			
				@SuppressWarnings("deprecation")
				int anno1 = persona.getDataIscrizione().getYear();
				@SuppressWarnings("deprecation")
				int anno2 = tdate.getYear();
				if(anno1<anno2) {
					if(persona.getCattivo_pagatore() == null) {
						if(persona.getBudget()>=50) {
							persona.setBudget(persona.getBudget()-50);
							personaRepository.save(persona);
							return "true, è stata rinnovata l'iscrizione!";
					} else { persona.setCattivo_pagatore("1"); personaRepository.save(persona);
					return tipologiaPersona.getDescrizione()+", fondi non sufficienti per rinnovare l'iscrizione, ora cattivo pagatore.";}
				
				}
					if(persona.getCattivo_pagatore().equals("1")) {
						persona.setBudget(persona.getBudget()-50);
						persona.setCattivo_pagatore(null);
						personaRepository.save(persona);
						return "true, eri un cattivo pagatore, ma ora è stata rinnovata l'iscrizione!";
					}				
				
				}	
			return "false";
			}
		}
		catch (Exception e) {
			finale = e.getMessage();
			return e.getMessage();
		}
		return finale;
	}
	
	//TROVIAMO LA PERSONA TRAMITE CODICE FISCALE.
	@Override
	public Persona getPersonaByCodiceFiscale(String codiceFiscale) {
		return personaRepository.getById(codiceFiscale);		
	}
	
	//METODO CHE TROVA L'ESISTENZA DI UNA PERSONA, QUINDI TRUE O FALSE, TRAMITE IL SUO CODICEFISCALE.
	@Override
	public boolean existsById(String codiceFiscale) {
		return personaRepository.existsById(codiceFiscale);
	}
	
	//(PUNTO DELLE INFO SUGLI UTENTI RITARDATARI) TROVA SEMPLICEMENTE LE PERSONE LA QUALE DATA DI RESTITUZIONE
	//E' PRIMA DELLA DATA ODIERNA, COSI DA CAPIRE CHI ANCORA NON HA RICONSEGNATO L'AUTO.
	@Override
	public Collection<Persona> utentiNotDoneRiconsegna() {
		Collection<Persona> utentiNotRiconsegnato = new ArrayList<Persona>();
		Collection<Persona> persone = personaRepository.findAll();
		Date tdate = new Date(System.currentTimeMillis());
		for(Persona persona: persone) {
			if(persona.getData_fissata_restituzione() == null) {
				continue;
			}
			
			if(persona.getData_fissata_restituzione().before(tdate)) {
					utentiNotRiconsegnato.add(persona);
			}
		}
		return utentiNotRiconsegnato;
	}
	
}
