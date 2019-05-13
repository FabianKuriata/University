import java.util.ArrayList;
import java.util.List;

public class Pamiec {
	int ilosc_stron; // pamieæ wirtualna
	int ilosc_ramek; // pamieæ fizyczna (RAM)
	List<Ramka> lista_ramek;
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
	
}
