import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
	public static int ileProcesow = 10;

	public static void main(String[] args){
		int stron = 300;
		int ramek = 100;
		int []liczba_odwolan = new int[ileProcesow];
		Pamiec pamiec = new Pamiec(stron, ramek);
		Random random = new Random();
		int sposob_rozdania_ramek = 1;   // 1-proporcjonalnie,  2-przydzial rowny,  3-Sterowanie czestoscia bledow, 4-model strefowy 
		List<Proces> lista_procesow = new ArrayList<Proces>();
		int suma = 0;
		
		////////// GENEROWANIE ODWOLAN PROCESOW ////////////////////
		for(int i = 0; i<ileProcesow; i++){
			liczba_odwolan[i] = random.nextInt(95)+5;
			lista_procesow.add(new Proces(liczba_odwolan[i]));
			lista_procesow.get(i).rozdajRamki(1,i+1);
			
		}
		Collections.shuffle(Proces.globalna_lista);  // rozmiesznie odwolan
		
		
		///////////// PROPORCJONALNY /////////////////////////////
		for(int i = 0; i<Proces.globalna_lista.size(); i++){
			//System.out.print(Proces.globalna_lista.get(i).nr_strony + " "+Proces.globalna_lista.get(i).nr_procesu + "  ");
		}
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("Podzia³ Proporcjonalny");
		System.out.println("---------------------------------");
		Lru lru = new Lru(Proces.globalna_lista);
		for(int i = 0; i < ileProcesow; i++){
			System.out.println((i+1) + " proces");
			System.out.println("----------------");
			System.out.println("Brakow dla: "+ lru.ileBrakow(i+1));
			System.out.println();
			suma += lru.ileBrakow(i+1);
		}
		System.out.println("Globalnie :" + suma);
		///////// CZYSZCZENIE STATYCZNYCH LIST ////////////////
		Proces.czyscGlobalnaListe();
		Pamiec.czyscRam();
		
        ///////////////////  RÓWNY ///////////////////////////////
		for(int i = 0; i<ileProcesow; i++){
			lista_procesow.get(i).rozdajRamki(2,i+1);
			//System.out.println(lista_procesow.get(i).ilosc_ramek);
		    //lista_procesow.get(i).display();
			//System.out.println();
		}
		suma = 0;
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("Podzia³ równy");
		System.out.println("---------------------------------");
		Lru lru2 = new Lru(Proces.globalna_lista);
		for(int i = 0; i < ileProcesow; i++){
			System.out.println((i+1) + " proces");
			System.out.println("----------------");
			System.out.println("Brakow dla: "+ lru2.ileBrakow(i+1));
			System.out.println();
			suma += lru2.ileBrakow(i+1);
		}
		System.out.println("Globalnie :"+ suma);
		///////// CZYSZCZENIE STATYCZNYCH LIST ////////////////
		Proces.czyscGlobalnaListe();
		Pamiec.czyscRam();
		System.out.println();
		///////////// SPRAWDZANIE CZESTOŒCI B£EDÓW //////////////////
		for(int i = 0; i<ileProcesow; i++){
			lista_procesow.get(i).rozdajRamki(3,i+1);
		}
		suma = 0;
		System.out.println("---------------------------------");
		System.out.println("Sprawzanie czestosci bledow");
		System.out.println("---------------------------------");
		Lru lru3 = new Lru(Proces.globalna_lista);
		for(int i = 0; i < ileProcesow; i++){
			System.out.println((i+1) + " proces");
			System.out.println("----------------");
			System.out.println("Brakow dla: "+ lru3.ileBrakow(i+1));
			System.out.println();
			suma += lru3.ileBrakow(i+1);
		}
		System.out.println("Globalnie :"+ suma);
	}
}
