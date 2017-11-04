
public class Urzednik extends Pracownik {
	
	protected double premia = 0;
	
	public Urzednik(String imie, String nazwisko, double czescEtatu, Etat etat, double premia) {
		super(imie, nazwisko, czescEtatu, etat);
		
		if(premia < 0 ){
			System.out.println("Premia nie mo¿e byæ mniejsza od zera.");
			throw new IllegalArgumentException();
		}

		this.premia = premia;
		
	}
	
	public double wynagrodzenie(){

		double bonus = (((premia)/100.0)*(etat.placa()*czescEtatu)); 
		return etat.placa()*czescEtatu + bonus;
	
	}
	
	public String daneGrupa(){
		
		return nazwisko()+" | urzêdnik | "+czescEtatu;
		
	}
	
}
