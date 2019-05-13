
public class Main {

	public static void main(String[] args) {
		
		//Tworzenie etatów    
		
		Etat etat1 = new Etat(false,15,140);
		Etat etat2 = new Etat(false,20,140);
		Etat etat3 = new Etat(true,2400,100);
		
		//
		
		//Tworzenie pracy
		
		Praca p = new Praca();
		
		//
		
		//Dodawanie pracowników
		
		p.dodajRobotnika("Jan", "Kowalski", 0.5, 60, etat2);
		p.dodajRobotnika("Micha³", "Pa³ka", 1.0, 120, etat1);
		p.dodajRobotnika("Micha³", "Nowak", 1.0, 140, etat1);
		p.dodajRobotnika("Kuba", "Rzeka", 0.5, 60, etat1);
		p.dodajRobotnika("Robert", "Groszek", 1.25, 180, etat1);
		p.dodajUrzednika("Janusz","Bela", 1.0, etat3, 7);
		p.dodajUrzednika("Jacek","Tatar", 0.75, etat3, 5);
		
		//
		
		//Wypisywanie danych
		
			//System.out.println(p.pracownik("Nowak").wynagrodzenie());
		
			//System.out.println(p.pracownik("Rzeka").czyUrzednik());
		
			//System.out.println(p.czyIstnieje("Kuk³a"));
		
			//System.out.println(p.sumaWyplatUrzednikow());
		
			//p.usunPracownika("Kowalski");
			
			//System.out.println(p.sumaWyplat());
			
			//System.out.println(p.sumaWyplatUrzednikow());
			
			//System.out.println(p.sumaWyplatRobotnikow());
			
			//System.out.println(p.iluUrzednikow());
		
			//System.out.println(p.iluPracownikow());
			
			//System.out.println(p.iluRobotnikow());
			
			p.listaPlac();
		
			//p.listaPracownikow();
			
			//p.listaUrzednikow();
			
			//p.listaRobotnikow();

		//
			
	}

}
