import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Student[] lista;
		Student studenci;
		
		lista = new Student[2];
		studenci = new Student();
		studenci.stworzListe(lista);
		lista[0].wysiwetl();
		System.out.println();
		System.out.println(studenci.toString());
		
		System.out.println("------------------------------------");
		System.out.printf("|"+"%-16s","Nazwisko");
		System.out.printf("|"+"%-16s","Imię");
		System.out.printf("|"+"%-12s","Album");
		System.out.printf("|"+"%-6s","Rok");
		System.out.printf("|"+"%-6s","Średnia"+"|");
		System.out.println();
		System.out.println("------------------------------------");
		for(int i = 0; i < lista.length; i++)
		{
			System.out.println(lista[i]+" |");
		}
		System.out.println("------------------------------------");
	}
}
