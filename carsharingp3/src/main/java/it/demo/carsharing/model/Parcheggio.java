package it.demo.carsharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parcheggio")
public class Parcheggio {
	
	@Id
	private String idParcheggio;
	private String localita;
	private String nome_parcheggio;
	
	public String getIdParcheggio() {
		return idParcheggio;
	}
	public void setIdParcheggio(String idParcheggio) {
		this.idParcheggio = idParcheggio;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public String getNome_parcheggio() {
		return nome_parcheggio;
	}
	public void setNome_parcheggio(String nome_parcheggio) {
		this.nome_parcheggio = nome_parcheggio;
	}

}
