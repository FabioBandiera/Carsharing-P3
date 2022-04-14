package it.demo.carsharing.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	private String codiceFiscale;
	private Date dataNascita;
	private String targaCarAssociata;
	private String tipologiaPersona;
	private int budget;
	private Date dataIscrizione;
	private Date dataAssociazioneCar;
	private String nome;
	private String cognome;
	private String cattivo_pagatore;
	private Date data_fissata_restituzione;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getTargaCarAssociata() {
		return targaCarAssociata;
	}
	public void setTargaCarAssociata(String targaCarAssociata) {
		this.targaCarAssociata = targaCarAssociata;
	}
	
	public String getTipologiaPersona() {
		return tipologiaPersona;
	}
	public void setTipologiaPersona(String tipologiaPersona) {
		this.tipologiaPersona = tipologiaPersona;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public Date getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	public Date getDataAssociazioneCar() {
		return dataAssociazioneCar;
	}
	public void setDataAssociazioneCar(Date dataAssociazioneCar) {
		this.dataAssociazioneCar = dataAssociazioneCar;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCattivo_pagatore() {
		return cattivo_pagatore;
	}
	public void setCattivo_pagatore(String cattivo_pagatore) {
		this.cattivo_pagatore = cattivo_pagatore;
	}
	public Date getData_fissata_restituzione() {
		return data_fissata_restituzione;
	}
	public void setData_fissata_restituzione(Date data_fissata_restituzione) {
		this.data_fissata_restituzione = data_fissata_restituzione;
	}
	
	
}
