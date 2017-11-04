import java.util.Scanner;

public class Menu {
	static public void main(String[] args) {

		Lista cards = null;
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Utworzenie listy");
			System.out.println("2. Wyœwietlenie listy");
			System.out.println("3. Wyswietlenie liczby elementów listy");
			System.out.println("4. Wyœwietlenie kart o podanej wartoœci");
			System.out.println("5. Wyœwietlenie kart o podanym kolorze");
			System.out.println("6. Usuñ wszystkie karty o podanym kolorze");
			
			Scanner scan = new Scanner(System.in);
			int choose = scan.nextInt();
			System.out.println();
			switch (choose) {
			case 1: {
				cards = new Lista();
				break;
			}
			case 2: {
				cards.wyswietl();
				if(cards.pusta()){
					System.out.println("Lista jest pusta");
				}
				break;
			}
			case 3: {
				System.out.println(cards.liczbaElementow());
				break;
			}
			case 4: {
				System.out.println("Podaj wartoœæ karty");
				int v = scan.nextInt();
				cards.szukajTyp(v);
				break;
			}
			case 5: {
				System.out.println("Podaj wartoœæ koloru");
				int c = scan.nextInt();
				cards.szukajKolor(c);
				break;
			}
			case 6: { 
				System.out.println("Podaj kolor kart, które maj¹ zostaæ usuniête");
				int c = scan.nextInt();
				cards.usun(c);
				break;
			}
			default: System.out.println("Nie ma takiej opcji");
			}

			System.out.println();
			System.out.println();
		}

	}
}
