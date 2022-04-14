package it.demo.carsharing.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lista_prenotazioni")
public class ListaPrenotazioni {
	//IL CONCETTO è LO STESSO DI CAR, QUINDI DA QUI IN POI NON DESCRIVERò PIù I MODEL, MA AGGIUNGO CHE I GETTER E SETTER
	//SONO STATI AGGIUNTI COSI DA POTER MODIFICARE, OVE NECESSARIO, L'OGGETTO ACCEDENDOVI DA PUNTI CHE HANNO I PRIVILEGI PER 
	//FARLO COME IL CONTROLLER.
	
	@Id
	private String ticket;
	private String cf_utente;
	private String targa_auto;
	private Date data_inizio_prenotazione;
	private Date data_riconsegna_auto;
	private String id_parcheggio_prestabilito;
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getCf_utente() {
		return cf_utente;
	}
	public void setCf_utente(String cf_utente) {
		this.cf_utente = cf_utente;
	}
	public String getTarga_auto() {
		return targa_auto;
	}
	public void setTarga_auto(String targa_auto) {
		this.targa_auto = targa_auto;
	}
	public Date getData_inizio_prenotazione() {
		return data_inizio_prenotazione;
	}
	public void setData_inizio_prenotazione(Date data_inizio_prenotazione) {
		this.data_inizio_prenotazione = data_inizio_prenotazione;
	}
	public Date getData_riconsegna_auto() {
		return data_riconsegna_auto;
	}
	public void setData_riconsegna_auto(Date data_riconsegna_auto) {
		this.data_riconsegna_auto = data_riconsegna_auto;
	}
	public String getId_parcheggio_prestabilito() {
		return id_parcheggio_prestabilito;
	}
	public void setId_parcheggio_prestabilito(String id_parcheggio_prestabilito) {
		this.id_parcheggio_prestabilito = id_parcheggio_prestabilito;
	}
	
	

}
