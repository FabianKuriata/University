import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rand {
	int brak_stron;
	int id_ramki;
	Pamiec pamiec;
	List<Odwolanie> lista;
	Random random = new Random();
	
	public Rand(Pamiec pamiec, List<Odwolanie> lista2){
		brak_stron = 0;
		id_ramki = 0;
		this.pamiec = new Pamiec(pamiec.ilosc_stron, pamiec.ilosc_ramek);
		this.lista = new ArrayList<Odwolanie>();
		for(int i = 0; i < lista2.size(); i++){
			Odwolanie o = new Odwolanie();
			this.lista.add(o);
			this.lista.get(i).nr_strony = lista2.get(i).nr_strony;
			this.lista.get(i).bit_poprawnosci = lista2.get(i).bit_poprawnosci;
		}
		
		for(int i = 0; i < lista.size(); i++){
			if(czyJestRozna(lista.get(i).nr_strony) && this.pamiec.lista_ramek.get(id_ramki).isPusta()){
				this.pamiec.lista_ramek.get(id_ramki).wartosc = lista.get(i).nr_strony;
				this.pamiec.lista_ramek.get(id_ramki).pusta = false;
				brak_stron++;
				id_ramki++;
				if(id_ramki >= this.pamiec.ilosc_ramek){
					id_ramki = 0;
				}
			}
			else if(czyJestRozna(lista.get(i).nr_strony)){
				id_ramki = random.nextInt(this.pamiec.lista_ramek.size());
				this.pamiec.lista_ramek.get(id_ramki).wartosc = lista.get(i).nr_strony;
				brak_stron++;
			}
		}
	}
	
	public boolean czyJestRozna(int nr_strony){
		for(int i = 0; i < this.pamiec.lista_ramek.size(); i++){
			if(this.pamiec.lista_ramek.get(i).wartosc == nr_strony){
				return false;
			}
		}
		return true;
	}
	public int ileBrakow(){
		return brak_stron;
	}
}
