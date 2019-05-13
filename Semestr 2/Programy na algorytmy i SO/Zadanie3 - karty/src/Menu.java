import java.util.Scanner;

public class Menu {
	static public void main(String[] args) {

		Lista cards = null;
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Utworzenie listy");
			System.out.println("2. Wy�wietlenie listy");
			System.out.println("3. Wyswietlenie liczby element�w listy");
			System.out.println("4. Wy�wietlenie kart o podanej warto�ci");
			System.out.println("5. Wy�wietlenie kart o podanym kolorze");
			System.out.println("6. Usu� wszystkie karty o podanym kolorze");
			
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
				System.out.println("Podaj warto�� karty");
				int v = scan.nextInt();
				cards.szukajTyp(v);
				break;
			}
			case 5: {
				System.out.println("Podaj warto�� koloru");
				int c = scan.nextInt();
				cards.szukajKolor(c);
				break;
			}
			case 6: { 
				System.out.println("Podaj kolor kart, kt�re maj� zosta� usuni�te");
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
