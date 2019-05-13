
public class Main {
	public static void main(String[] args){
		int dlugosc = 1000;
		int liczba_stron = 300;
		int[] liczba_ramek = new int[3];
		liczba_ramek[0] = 300;
		liczba_ramek[1] = 107;
		liczba_ramek[2] = 100;
		
		
		Generator lista_odwolan = new Generator(dlugosc, liczba_stron);
		
		for(int r = 0; r < liczba_ramek.length; r++){
			Pamiec pamiec;
			pamiec = new Pamiec(liczba_stron, liczba_ramek[r]);
			
			//lista_odwolan.display();
			System.out.println("Wyniki dla "+liczba_ramek[r]+" ramek");
			Fifo fifo = new Fifo(pamiec,lista_odwolan.lista_odwolan);
			
			System.out.println("Braków stron dla Fifo:" +fifo.ileBrakow());
			//System.out.println("Lista ramek po wykonaniu odwo³añ dla Fifo:" +fifo.pamiec.lista_ramek);
			
			Opt opt = new Opt(pamiec,lista_odwolan.lista_odwolan);
			//System.out.println();
			System.out.println("Braków stron dla OPT:" +opt.ileBrakow());
			//System.out.println("Lista ramek po wykonaniu odwo³añ dla OPT:" +opt.pamiec.lista_ramek);
			
			Lru lru = new Lru(pamiec,lista_odwolan.lista_odwolan);
			//System.out.println();
			System.out.println("Braków stron dla LRU:" +lru.ileBrakow());
			//System.out.println("Lista ramek po wykonaniu odwo³añ dla LRU:" +lru.pamiec.lista_ramek);
			
			Alru alru = new Alru(pamiec,lista_odwolan.lista_odwolan);
			//System.out.println();
			System.out.println("Braków stron dla ALRU:" +alru.ileBrakow());
			//System.out.println("Lista ramek po wykonaniu odwo³añ dla ALRU:" +alru.pamiec.lista_ramek);
			
			
			Rand rand = new Rand(pamiec,lista_odwolan.lista_odwolan);
			//System.out.println();
			System.out.println("Braków stron dla RAND:" +rand.ileBrakow());
			//System.out.println("Lista ramek po wykonaniu odwo³añ dla RAND:" +rand.pamiec.lista_ramek);
			//lista_odwolan.display();
			
			System.out.println();
			System.out.println();
			
		}
		
	}
}
