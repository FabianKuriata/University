import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Student[] lista;
		Uczelnia uczelnia = null;
		int liczbaStud = 0;
		
		Scanner czytaj = new Scanner(System.in);

		// Wpisywanie danych
		System.out.println("Podaj liczbe studentow");
		liczbaStud = czytaj.nextInt();
		lista = new Student[liczbaStud];
		for (int i = 0; i < lista.length; i++) {

			System.out.println("Id:" + (i + 1));
			lista[i] = new Student(czytaj.next(), czytaj.next(), czytaj.nextLong(), czytaj.nextInt(),
					czytaj.nextDouble());
		}
		czytaj.close();
		uczelnia = new Uczelnia(lista);
		Iterator itr = new Uczelnia(lista);
		for (int i = 0; i < 64; i++)
			System.out.print("-");
		System.out.println();
		System.out.printf("|" + "%-16s", "Nazwisko");
		System.out.printf("|" + "%-16s", "Imiê");
		System.out.printf("|" + "%-12s", "Album");
		System.out.printf("|" + "%-6s", "Rok");
		System.out.printf("|" + "%-9s", "Œrednia");
		System.out.print("|");
		System.out.println();
		
		for (int i = 0; i < 64; i++)
			System.out.print("-");
		System.out.println();
		
		itr.first();
		while(!itr.isDone()){
			System.out.println(itr.current());
			itr.next();
		}
		for (int i = 0; i < 64; i++)
			System.out.print("-");
		System.out.println();
		
	}
}
