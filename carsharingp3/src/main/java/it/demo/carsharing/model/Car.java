package it.demo.carsharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
	//QUESTA CLASSE DEFINISCE L'ASTRAZIONE DI CAR, COSI DA POTER INSTANZIARE UN OGGETTO DI TIPO AUTO.
	//LE VARIE ANNOTATIONS SERVONO A DEFINIRE CHE QUESTA CLASSE è UN'ENTITà CHE DEFINISCE LA TABELLA CAR DEL 
	//DB, CON ID TARGA TI TIPO VARCHAR.

	@Id
	private String targa;
	
	private String cfutente_associato;
	private String id_parcheggio;
	
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public String getCfutente_associato() {
		return cfutente_associato;
	}
	public void setCfutente_associato(String cfutente_associato) {
		this.cfutente_associato = cfutente_associato;
	}
	public String getId_parcheggio() {
		return id_parcheggio;
	}
	public void setId_parcheggio(String id_parcheggio) {
		this.id_parcheggio = id_parcheggio;
	}
	
	

}
