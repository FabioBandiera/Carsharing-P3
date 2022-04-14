package it.demo.carsharing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipologiapersona")
public class TipologiaPersona {
	
	@Id
	private String Id;
	private String Descrizione;
	
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione;
	}
	

}
