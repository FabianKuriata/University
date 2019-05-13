import java.util.Scanner;

public class Menu {
	static public void main(String[] args) {

		Deck cards = null;
		while (true) {
			System.out.println("Menu");
			System.out.println("1. Utworzenie listy");
			System.out.println("2. Wyœwietlenie listy");
			System.out.println("3. Wyswietlenie liczby elementów listy");
			System.out.println("4. Wyœwietlenie kart o podanej wartoœci");
			System.out.println("5. Wyœwietlenie kart o podanym kolorze");

			Scanner scan = new Scanner(System.in);
			int choose = scan.nextInt();
			System.out.println();
			switch (choose) {
			case 1: {
				cards = new Deck();
				break;
			}
			case 2: {
				cards.display();
				break;
			}
			case 3: {
				cards.amount();
				break;
			}
			case 4: {
				System.out.println("Podaj wartoœæ karty");
				int v = scan.nextInt();
				cards.displayV(v);
				break;
			}
			case 5: {
				System.out.println("Podaj wartoœæ koloru");
				int c = scan.nextInt();
				cards.displayC(c);
				break;
			}
			default: System.out.println("Nie ma takiej opcji");
			}

			System.out.println();
			System.out.println();
		}

	}
}
