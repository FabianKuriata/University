public abstract class Pracownik {
	
	protected String imie,nazwisko;
	protected double czescEtatu;
	protected Etat etat;
	
	public Pracownik(String imie, String nazwisko, double czescEtatu, Etat etat){
		
		if(czescEtatu <= 0){
			System.out.println("Etat nie mo¿e byæ mniejszy od zera.");
			throw new IllegalArgumentException();
		}
		if(nazwisko.equals("")){
			System.out.println("Pracownik musi posiadaæ nazwisko.");
			throw new IllegalArgumentException();
		}
		
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.etat = etat;
		this.czescEtatu = czescEtatu;
		
	}
	
	public String imie(){
		
		return imie;
		
	}
	
	public String nazwisko(){
		
		return nazwisko;
		
	}
	
	public double czescEtatu(){
		
		return czescEtatu;
		
	}
	
	public String dane(){
		
		return nazwisko()+" | "+czescEtatu;
		
	}
	
	public abstract String daneGrupa();
	public abstract double wynagrodzenie();
	
}
