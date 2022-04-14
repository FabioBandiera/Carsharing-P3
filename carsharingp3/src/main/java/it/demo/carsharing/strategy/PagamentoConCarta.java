package it.demo.carsharing.strategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class PagamentoConCarta implements StrategyOfPayment{
	//QUESTA CLASSE CREA UN METODO DI PAGAMENTO TRAMITE CARTA. ESSA VIENE POI RICHIAMATA DAL METODO RICONSEGNACAR
	//IN PARCHEGGIO SERVICEIMPL NEL CASO SI PAGHI TRAMITE CARTA.
	
	Date tdate = new Date(System.currentTimeMillis());
	LocalDate tdateLD = LocalDate.now();
	int tariffa = 15;
	int mora = 20;
	int useDays = 0;
	int calculatemorelessdays = 0;
	int total = 0;
	//Nel caso di pagamento con carta non avremo detrazioni relative all'utilizzo della carta. Quindi si paga solo il dovuto.
	public int pagaCon(Date data_riconsegna, LocalDate data_riconsegnaLD, LocalDate data_inizio_usoLD) {
	if(data_riconsegna.equals(tdate) || data_riconsegna.before(tdate)) {
		//Conteggio dei giorni di usufrutto senza tasse. 1 giorno = 15 euro.
		useDays =(int) ChronoUnit.DAYS.between(data_inizio_usoLD, tdateLD);
		total = (useDays*15);
		
	}else if(data_riconsegna.after(tdate)) { 
		//Conteggio con mora per ogni giorno. Ogni giorno di mora = 20.
		useDays =(int) ChronoUnit.DAYS.between(data_inizio_usoLD, tdateLD);
		calculatemorelessdays = (int) ChronoUnit.DAYS.between(data_riconsegnaLD, tdateLD);
		useDays = useDays + calculatemorelessdays;
		total = ((useDays*tariffa) - (calculatemorelessdays*tariffa)) + (calculatemorelessdays*mora);
	}
	return total;
	}
}
