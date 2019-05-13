
public class Main {
	public static void main(String[] args){
		Macierz a = new Macierz(4,4);
		Macierz b = new Macierz(4,4);
		
		a.insertElement(1, 1, 1);
		a.insertElement(1, 3, 1);
		a.insertElement(1, 3, 2);
		a.insertElement(1, 3, 3);
		a.insertElement(2, 2, 4);
		//a.addRandomValues();
		
		b.insertElement(2, 2, 2);
		b.insertElement(-1, 3, 2);
		b.insertElement(2, 1, 3);
		b.insertElement(1, 1, 4);
		b.insertElement(1, 4, 4);
		a.printValues();
		b.printValues();
		System.out.println("SUMA");
		Macierz.dodajMacierze(a, b).printValues();
		
		System.out.println();
	}
}
