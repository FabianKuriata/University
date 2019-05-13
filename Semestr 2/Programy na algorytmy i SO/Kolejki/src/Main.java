import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Kontener kontener = null;
		int liczba_procesow = 6;
		int liczba_testow = 3;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj liczb� proces�w");
		liczba_procesow = scan.nextInt();
		System.out.println("Podaj liczb� test�w");
		liczba_testow = scan.nextInt();

		for (int j = 0; j < liczba_testow; j++) // liczba pr�b
		{
			kontener = new Kontener();
			kontener.lista_procesow.clear();
			kontener.stworz(liczba_procesow);

			// sortowanie wedlug momentu_zgloszenia
			Collections.sort(Kontener.lista_procesow);

			// wygenerowane procesy po sortowaniu
			//kontener.wyswietl();
			System.out.println();
			System.out.println();
			// FCFS
			kontener.wybierz(Fcfs.class);
			//kontener.wyswietl();
			//System.out.println("�rednia FCFS: " + Kontener.sredniaFcfs);
			//System.out.println();
			//System.out.println();

			// ROT
			kontener.wybierz(Rot.class);
			//kontener.wyswietl();
			//System.out.println();
			//System.out.println("�rednia ROT: " + Kontener.srednia);
			//System.out.println();
			//System.out.println();
			
			// SJFb
			kontener.wybierz(Sjfb.class);
			//kontener.wyswietl();
			//System.out.println();
			//System.out.println("�rednia SJFb: " + Kontener.srednia);
			//System.out.println();
			//System.out.println();

			// SJFw
			kontener.wybierz(Sjfw.class);
			//kontener.wyswietl();
			//System.out.println();
			//System.out.println("�rednia SJFw: " + Kontener.srednia);
			//System.out.println();
			//System.out.println();

		}

		System.out.println();
		System.out.println();
		System.out.println("�rednia g�owna FCFS:" + Kontener.suma_Fcfs / liczba_testow);
		System.out.println("�rednia g�owna SJFb:" + Kontener.suma_Sjfb / liczba_testow);
		System.out.println("�rednia g�owna SJFw:" + Kontener.suma_Sjfw / liczba_testow);
		System.out.println("�rednia g�owna ROT:" + Kontener.suma_Rot / liczba_testow);
	}
}
