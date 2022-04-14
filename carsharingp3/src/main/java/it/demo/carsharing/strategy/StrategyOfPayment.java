package it.demo.carsharing.strategy;

import java.time.LocalDate;
import java.util.Date;

public interface StrategyOfPayment {
	//QUESTA INTERFACCIA FA SI CHE LO STRATEGY POSSA ESSERE DEFINITO.
	
	public int pagaCon(Date data_riconsegna, LocalDate data_riconsegnaLD, LocalDate data_inizio_usoLD);
}
