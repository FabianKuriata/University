import java.util.ArrayList;
import java.util.List;

public class Firma {
	
	int liczba;
	int maxKlientow;
	List<Magazyn> firma;
	
	public Firma(int liczba, int maxKlientow){
		this.maxKlientow = maxKlientow;
		this.liczba = liczba;
		firma = new ArrayList<Magazyn>();
		for(int i = 0; i < liczba; i++){
			Magazyn magazyn = new Magazyn(i+1);
			firma.add(magazyn);
		}
	}
	
	void dodajKlienta(Klient customer, Kolejka products){
		for(int i = 0; i < liczba; i++){
			if(firma.get(i).ileKlientow < maxKlientow){
				firma.get(i).customerRequest(customer, products);
				return;
			}
			
			if(i+1 == liczba){
				System.out.println("Brak miejsca w magazynie dla klienta");
			}
		}
		
	}
	
}
