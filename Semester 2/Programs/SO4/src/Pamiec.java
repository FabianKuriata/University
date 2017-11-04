import java.util.ArrayList;
import java.util.List;

public class Pamiec {
	static int ilosc_stron; // pamieæ wirtualna
	static int ilosc_ramek; // pamieæ fizyczna (RAM)
	static List<Ramka> lista_ramek;
	public Pamiec(int stron, int ramek){
		ilosc_stron = stron;
		ilosc_ramek = ramek;
		lista_ramek = new ArrayList<Ramka>();
		for(int i = 0; i < ilosc_ramek; i++){
			lista_ramek.add(new Ramka());
		}
	}

	public int getIlosc_stron() {
		return ilosc_stron;
	}

	public void setIlosc_stron(int ilosc_stron) {
		this.ilosc_stron = ilosc_stron;
	}

	public int getIlosc_ramek() {
		return ilosc_ramek;
	}

	public void setIlosc_ramek(int ilosc_ramek) {
		this.ilosc_ramek = ilosc_ramek;
	}
	
	public void displayRamki(){
		for(Ramka temp:lista_ramek){
			System.out.println(temp.nr_procesu + " ");
		}
	}
	
	public static void czyscRam(){
		for(int i = 0; i < ilosc_ramek; i++){
			lista_ramek.get(i).pusta = true;
			lista_ramek.get(i).wartosc = -1;
			lista_ramek.get(i).nr_procesu = -1;
		}
	}
}
