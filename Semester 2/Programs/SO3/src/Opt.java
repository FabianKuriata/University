import java.util.ArrayList;
import java.util.List;

public class Opt {
	int brak_stron;
	int id_ramki;
	Pamiec pamiec;
	List<Odwolanie> lista;
	
	public Opt(Pamiec pamiec, List<Odwolanie> lista2){
		brak_stron = 0;
		id_ramki = 0;
		
		// kopiowanie
		this.pamiec = new Pamiec(pamiec.ilosc_stron, pamiec.ilosc_ramek);
		this.lista = new ArrayList<Odwolanie>();
		for(int i = 0; i < lista2.size(); i++){
			Odwolanie o = new Odwolanie();
			this.lista.add(o);
			this.lista.get(i).nr_strony = lista2.get(i).nr_strony;
			this.lista.get(i).bit_poprawnosci = lista2.get(i).bit_poprawnosci;
		}
		
		/// Wpisywanie do pustych ramek
		for(int i = 0; i < lista.size(); i++){
			if(czyJestRozna(lista.get(i).nr_strony) && this.pamiec.lista_ramek.get(id_ramki).isPusta()){
				this.pamiec.lista_ramek.get(id_ramki).wartosc = this.lista.get(i).nr_strony;
				this.pamiec.lista_ramek.get(id_ramki).pusta = false;
				brak_stron++;
				id_ramki++;
				if(id_ramki > this.pamiec.lista_ramek.size()-1)
					id_ramki = 0;
				
				//System.out.println("Jestem");
			}
			else if(czyJestRozna(this.lista.get(i).nr_strony)){
				id_ramki = znajdzNajdalszy(i);
				this.pamiec.lista_ramek.get(id_ramki).wartosc = this.lista.get(i).nr_strony;
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
	
	public int znajdzNajdalszy(int k){
		int id = 0;
		int naj = 0;
		int odleglosc = 0;
		for(int l = 0; l < this.pamiec.lista_ramek.size(); l++){
			odleglosc = 0;
			for(int j = k+1; j < this.lista.size(); j++){
				if(this.pamiec.lista_ramek.get(l).wartosc == this.lista.get(j).nr_strony)
					break;
				else
					odleglosc++;
				
			}
			if(odleglosc > naj){
				naj = odleglosc;
				id = l;
			}
		}
		return id;
	}
	public int ileBrakow(){
		return brak_stron;
	}
}
