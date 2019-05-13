
public class Robotnik extends Pracownik {
	
	protected double godziny;
	
	public Robotnik(String imie, String nazwisko, double czescEtatu, double godziny, Etat etat){
		super(imie, nazwisko, czescEtatu, etat);
		
		if(godziny <= 0 ){
			System.out.println("Godziny nie mog¹ byæ mniejsze lub równe zero.");
			throw new IllegalArgumentException();
		}

		this.godziny = godziny;
		
	}
	
	public double wynagrodzenie(){

		if(godziny == 0){return 0;}
		
		double nadgodziny = ((etat.limitGodzin() * czescEtatu)-godziny)*-1;
		double bonus = 0;
		
		if(nadgodziny > 0){
			
			bonus = nadgodziny*(etat.placa()*1.5); 
			
		}
		
		return etat.placa()*godziny + bonus;
			
	}
	
	public String daneGrupa(){
		
		return nazwisko()+" | robotnik | "+czescEtatu;
		
	}
	
}
