import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Student[] lista;

		int liczbaStud = 0;

		Scanner czytaj = new Scanner(System.in);

		// Wpisywanie danych
		System.out.println("Podaj liczbe studentow");
		liczbaStud = czytaj.nextInt();
		lista = new Student[liczbaStud];
		for (int i = 0; i < lista.length; i++) {

			System.out.println("Id:" + (i+1));
			lista[i] = new Student(czytaj.next(), czytaj.next(), czytaj.nextLong(), czytaj.nextInt(), czytaj.nextDouble());
		
		}
		czytaj.close();

		System.out.println();
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
		for (int i = 0; i < lista.length; i++) {
			System.out.println(lista[i] + " |");
		}
		for (int i = 0; i < 64; i++)
			System.out.print("-");
		System.out.println();

		// zapis i odczyt danych
		try {
			FileOutputStream fos = new FileOutputStream("D:\\studenci.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			FileInputStream fis = new FileInputStream("D:\\studenci.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// lista[0].wyswietl();
			System.out.println();
			oos.writeInt(liczbaStud);
			for (int i = 0; i < lista.length; i++) {
				
				oos.writeObject(lista[i]);
			}
			oos.close();
			liczbaStud = ois.readInt();
			for (int i = 0; i < lista.length; i++) {
				
				lista[i] = (Student) ois.readObject();

				lista[i].wyswietl();
				System.out.println();
			}
			System.out.println("Liczba Studentów : " + liczbaStud);
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
