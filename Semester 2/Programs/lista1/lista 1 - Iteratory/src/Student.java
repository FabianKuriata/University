
public class Student {
	private String nazwisko;
	private String imie;
	private long album;
	private int rok;
	private double srednia;
	
	public Student(){
		nazwisko = null;
		imie = null;
		album = -1;
		rok = -1;
		srednia = -1;
	}
	
	public Student(String nazwisko, String imie, long album, int rok, double srednia){
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.album = album;
		this.rok = rok;
		this.srednia = srednia;
	}
	
	public String toString(){
		String n = String.format("| "+"%-15s", nazwisko);
		   String i = String.format("| "+"%-15s", imie);
		   String a = String.format("| "+"%11d", album);
		   String r = String.format("| "+"%5d", rok);
		   String s = String.format("|"+"%8.1f", srednia);
		   
		   return n + i + a + r + s;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public long getAlbum() {
		return album;
	}

	public void setAlbum(long album) {
		this.album = album;
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public double getSrednia() {
		return srednia;
	}

	public void setSrednia(double srednia) {
		this.srednia = srednia;
	}
	
}
