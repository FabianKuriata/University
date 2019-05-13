
public class Main {
	
	public static void main(String[] args){
		Klient maciek = new Klient("Maciej");
		Klient jasiek = new Klient("Janek");
		Klient wacek = new Klient("Wacek");
		Klient jacek = new Klient("Jacek");
		//Magazyn magazyn = new Magazyn();
		Firma firma = new Firma(3, 2);
		maciek.order("Ziemniaki", 2);
		maciek.order("Twaróg", 4);
		maciek.order("Marchew", 4);
		maciek.order("Pomidor", 4);
		maciek.order("Kawa", 4);

		jasiek.order("Bu³ki", 2);
		jasiek.order("Wino", 4);
		
		wacek.order("Kaszanka", 3);
		wacek.order("Kasza", 2);
		
		jacek.order("Majoznez", 4);
		firma.dodajKlienta(maciek, maciek.products);
		firma.dodajKlienta(jasiek, jasiek.products);
		firma.dodajKlienta(wacek, wacek.products);
		firma.dodajKlienta(jacek, jacek.products);
		
		for(int i = 0; i < firma.firma.size(); i++){
			System.out.println(firma.firma.get(i).nr_magazynu);
			firma.firma.get(i).display();
		}
		
		for(int i = 0; i < firma.firma.size()-1; i++){
			//System.out.println(firma.firma.get(i).nr_magazynu);
			for(int j = 0; j < firma.firma.get(i).ileKlientow; j++)
				firma.firma.get(i).doIt();
		}
		//magazyn.customerRequest(jasiek, jasiek.products);
		//magazyn.customerRequest(maciek, maciek.products);
		//magazyn.display();
		
		//int size = magazyn.fifo.size();
		//for(int i = 0; i < 2; i++)
			//magazyn.doIt();

		

	}
}
