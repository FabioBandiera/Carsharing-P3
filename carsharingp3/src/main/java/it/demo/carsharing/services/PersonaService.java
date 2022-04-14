package it.demo.carsharing.services;

import java.util.Collection;

import it.demo.carsharing.model.Persona;

public interface PersonaService {
	
	//In questa interfaccia ci sono i metodi che PersonaServiceImpl implementerà.

	public String subscribeToCarsharing(Persona persona) throws Exception;

	public String personaIsErogatore(String codiceFiscale);

	public String findTipologiaPersonaByCodiceFiscale(String codiceFiscale);

	public Persona getPersonaByCodiceFiscale(String codiceFiscale);

	public boolean existsById(String codiceFiscale);

	public Collection<Persona> utentiNotDoneRiconsegna();
}
