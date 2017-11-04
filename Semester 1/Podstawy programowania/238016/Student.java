import java.util.Scanner;

public class Student {
	private String nazwisko;
	private String imie;
	private long album;
	private int rok;
	private double srednia;
	
	public Student(){
		nazwisko = "brak";
		imie = "brak";
		album = -1;
		rok = -1;
		srednia = -1;
	}
	
	public void wysiwetl(){
		System.out.printf("%-15s", nazwisko);
		System.out.printf("%-15s", imie);
		System.out.printf("%5d", album);
		System.out.printf("%3d", rok);
		System.out.printf("%5.2f", srednia);
	}
	
   public String toString(){
	   String n = String.format("| "+"%-15s", nazwisko);
	   String i = String.format("| "+"%-15s", imie);
	   String a = String.format("| "+"%-11d", album);
	   String r = String.format("| "+"%-5d", rok);
	   String s = String.format("|"+"%-5.2f", srednia);
	   
	   return n + i + a + r + s;
   }
   
   
   // Tworzenie nowej listy studentów
   // TODO zabezpieczyc przed bledami
   public void stworzListe(Student[] lista){
	   	
	   	Scanner czytaj = new Scanner(System.in);
	   	
	   	for(int i = 0; i<lista.length; i++){
			lista[i] = new Student();
			System.out.println("Id studenta: "+(i));
			
			System.out.println("Wprowadz nazwisko");
			nazwisko = czytaj.nextLine();
			lista[i].setNazwisko(nazwisko);
			
			System.out.println("Wprowadz imie");
			imie = czytaj.nextLine();
			lista[i].setImie(imie);
			
			System.out.println("Wprowadz nr albumu");
			album = czytaj.nextLong();
			lista[i].setAlbum(album);
			
			System.out.println("Wprowadz rok studiów");
			rok = czytaj.nextInt();
			lista[i].setRok(rok);
			
			System.out.println("Wprowadz średnią");
			srednia = czytaj.nextDouble();
			lista[i].setSrednia(srednia);
			
		}
	   	czytaj.close();
   }
   
   // Gettery i settery
   
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