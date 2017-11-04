import java.util.HashMap;
import java.util.Map;

public class Praca {
	
	private static Map<String, Pracownik> pracownicy = new HashMap<>();
	
	public Praca(){}
	
	public void dodajRobotnika(String imie, String nazwisko, double czescEtatu, double godziny ,Etat etat){
		
		if(!czyIstnieje(nazwisko)){

			pracownicy.put(nazwisko,new Robotnik(imie, nazwisko, czescEtatu, godziny, etat));
			
		}else{
			
			System.out.println("Taki pracownik ju¿ istnieje: "+nazwisko);
			
		}
		
	}
	
	public void dodajUrzednika(String imie, String nazwisko, double czescEtatu, Etat etat, double premia){
		
		if(!czyIstnieje(nazwisko)){
			
			pracownicy.put(nazwisko,new Urzednik(imie, nazwisko, czescEtatu, etat, premia));
			
		}else{
			
			System.out.println("Taki pracownik ju¿ istnieje: "+nazwisko);
			
		}
		
	}

	public Pracownik pracownik(String nazwisko){
		
		return pracownicy.get(nazwisko);
		
	}
	
	public boolean czyIstnieje(String nazwisko){
		
		return pracownicy.containsKey(nazwisko);
		
	}
	
	public void usunPracownika(String nazwisko){
		
		if(czyIstnieje(nazwisko)){
		
			pracownicy.remove(nazwisko);
			System.out.println("Pracownik zostal usuniêty: "+nazwisko);
		
		}else{
			
			System.out.println("Pracownik nie istnieje");
			
		}
		
	}
	
	public double sumaWyplat(){
		
		return liczZarobki(2);
		
	}
	
	public double sumaWyplatUrzednikow(){
		
		return liczZarobki(1);
		
	}
	
	public double sumaWyplatRobotnikow(){
		
		return liczZarobki(0);
		
	}
	
	private double liczZarobki(int c){
		
		double r = 0;
		double u = 0;
		
		for (Map.Entry<String, Pracownik> entry : pracownicy.entrySet()) {
			
		    if(entry.getValue() instanceof Urzednik == true){
		    	u += entry.getValue().wynagrodzenie();
		    }else{
		    	r += entry.getValue().wynagrodzenie();
		    }
		    
		}
		
		switch (c) {
		
			case 0: return r;
			case 1: return u;
			case 2: return r+u;
		
		}
		
		return -1;
		
	}
	
	public int iluUrzednikow(){
		
		return liczKategorie(1);
		
	}
	
	public int iluRobotnikow(){
		
		return liczKategorie(0);
		
	}
	
	public int iluPracownikow(){
		
		return pracownicy.size();
		
	}
	
	private int liczKategorie(int c){
		
		int s = 0;
		
		for (Map.Entry<String, Pracownik> entry : pracownicy.entrySet()) {
			
			if(entry.getValue() instanceof Urzednik == true && c == 1){
				s++;
			}else if(entry.getValue() instanceof Urzednik == false && c == 0){
				s++;
			}
		    
		}
		
		return s;
		
	}
	
	public void listaPlac(){
		
		wydruk(3);
		
	}
	
	public void listaPracownikow(){
		
		wydruk(2);
		
	}
	
	public void listaUrzednikow(){
		
		wydruk(1);
		
	}
	
	public void listaRobotnikow(){
		
		wydruk(0);
		
	}
	
	private void wydruk(int c){
		
		switch(c){
		
			case 0:
				System.out.println("Lp | nazwisko | etat");
				break;
			case 1:
				System.out.println("Lp | nazwisko | etat");
				break;
			case 2:
				System.out.println("Lp | nazwisko | grupa | etat");
				break;
			case 3:
				System.out.println("Lp | nazwisko | grupa | etat | p³aca");
				break;
	
		}
		
		int i = 1;
		
		for (Map.Entry<String, Pracownik> entry : pracownicy.entrySet()) {
			
			switch(c){
			
				case 0:
					if(entry.getValue() instanceof Urzednik == false){
						System.out.println(i+" | "+entry.getValue().dane());
						i++;
					}
					break;
				case 1:
					if(entry.getValue() instanceof Urzednik == true){
						System.out.println(i+" | "+entry.getValue().dane());
						i++;
					}
					break;
				case 2:
					System.out.println(i+" | "+entry.getValue().daneGrupa());
					i++;
					break;
				case 3:
					System.out.println(i+" | "+entry.getValue().daneGrupa()+" | "+entry.getValue().wynagrodzenie());
					i++;
					break;
			}
		    
		}
		
	}
	
}
