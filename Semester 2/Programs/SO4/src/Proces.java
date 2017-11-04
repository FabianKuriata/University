import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Proces {
	List<Odwolanie> lista_odwolan = new ArrayList<Odwolanie>();
	static List<Odwolanie> globalna_lista = new ArrayList<Odwolanie>();
	static int[] ilosc_stron_procesu = new int[Main.ileProcesow];
	static int zakres_od = 0; // do losowania nr stron odwolan
	static int od_pro = 0;  // do proporocjonalnego
	int zakres_do = 0; // do losowania nr stron odwolan
	static int p = 1; // nr procesu
	int ilosc_ramek;
	Random losuj = new Random();
	
	static int[] dopuszczalne = new int[Main.ileProcesow];
	
	public Proces(int liczba_odwolan){
		ilosc_stron_procesu[p-1] = losuj.nextInt((Pamiec.ilosc_stron/Main.ileProcesow)-2)+2;
		zakres_do = ilosc_stron_procesu[p-1];
		System.out.println(p +" proces\n" + "Ilosc odwolan : " +liczba_odwolan + "\nIlosc pamieci wirtualnej: " + ilosc_stron_procesu[p-1]);
		System.out.println();
		// Generetor odwolan w procesie
		for(int i = 0; i<liczba_odwolan; i++){
			lista_odwolan.add(new Odwolanie());
			lista_odwolan.get(i).nr_strony = losuj.nextInt(zakres_do)+zakres_od;
			lista_odwolan.get(i).nr_procesu = p;
			globalna_lista.add(lista_odwolan.get(i));
		}
		zakres_od = zakres_do;
		p++;
	}
	
	public void rozdajRamki(int sposob, int nr_procesu){
		switch(sposob){
		case 1: proporcjonalny(nr_procesu);break;
		case 2: rowny(nr_procesu);break;
		case 3: czestoscBledow(nr_procesu);break;
		case 4: modelStrefowy();break;
		default: System.out.println("Nie ma takiego sposobu");
		}
	}
	private void modelStrefowy() {
		// TODO Auto-generated method stub
		
	}

	private void czestoscBledow(int nr_procesu) {
		
		// TODO Auto-generated method stub
		rowny(nr_procesu);
		Lru.sprawdzanieCzestosc = true;
		dopuszczalne[nr_procesu-1] = 3*ilosc_stron_procesu[nr_procesu-1]/2;
	}

	private void rowny(int nr_procesu) {
		// Przypisywanie ramek procesom
		ilosc_ramek = (Pamiec.ilosc_ramek)/Main.ileProcesow - ((Pamiec.ilosc_ramek)/4*Main.ileProcesow);
		//System.out.println(ilosc_ramek);
		int od;
		if(nr_procesu>1){
			od = ilosc_ramek*(nr_procesu-1);
		}
		else
			od = 0;
		for(int i = od; i < ilosc_ramek*(nr_procesu); i++){
			Pamiec.lista_ramek.get(i).nr_procesu = nr_procesu;
		}
		
	}

	private void proporcjonalny(int nr_procesu) {
		ilosc_ramek = (int) (Pamiec.ilosc_ramek *((double)ilosc_stron_procesu[nr_procesu-1]/(double)Pamiec.ilosc_stron)); // Ilosc ramek * (pamiec_wirtualna_procesu/cala_pamiec)
		for(int i = 0; i < ilosc_ramek;i++){
			Pamiec.lista_ramek.get(od_pro).nr_procesu = nr_procesu;
			od_pro++;
		}
	}

	public void display(){
		for(Odwolanie temp: lista_odwolan){
			System.out.print(temp + " ");
		}
	}
	
	public static void czyscGlobalnaListe(){
		for(int i = 0; i < globalna_lista.size(); i++){
			globalna_lista.get(i).bit_poprawnosci = false;
			globalna_lista.get(i).nadpisany = false;
			p = 1;
			od_pro = 0;
		}
	}
}
