import java.util.ArrayList;
import java.util.List;

public class Lru {
	int[] brak_stron;
	int[] id_ramki;
	int[] ramekDlaProcesu;
	int ramka;
	Pamiec pamiec;
	List<Odwolanie> lista;
	static boolean sprawdzanieCzestosc = false;
	
	public Lru(List<Odwolanie> lista2){
		brak_stron = new int[Main.ileProcesow];
		id_ramki = new int[Main.ileProcesow];
		ramekDlaProcesu = new int[Pamiec.ilosc_ramek];
		ramka = 0;
		id_ramki[0] = 0;
		for(int r = 1; r < Main.ileProcesow; r++){
			id_ramki[r] += id_ramki[r-1];
			for(int s = 0; s < Pamiec.lista_ramek.size(); s++){
				if(Pamiec.lista_ramek.get(s).nr_procesu == r){
					id_ramki[r]++;
					
				}
			}
		}
		for(int r = 0; r < Main.ileProcesow; r++){
			for(int s = 0; s < Pamiec.lista_ramek.size(); s++){
				if(Pamiec.lista_ramek.get(s).nr_procesu == r+1){
					ramka++;
				}
			}
			ramekDlaProcesu[r] = ramka;
		}
		// kopiowanie
		this.lista = new ArrayList<Odwolanie>();
		for(int i = 0; i < lista2.size(); i++){
			Odwolanie o = new Odwolanie();
			this.lista.add(o);
			this.lista.get(i).nr_strony = lista2.get(i).nr_strony;
			this.lista.get(i).bit_poprawnosci = lista2.get(i).bit_poprawnosci;
			this.lista.get(i).nr_procesu = lista2.get(i).nr_procesu;
			this.lista.get(i).nadpisany = lista2.get(i).nadpisany;
			this.lista.get(i).wykonane = lista2.get(i).wykonane;
		}
		
		/// Wpisywanie do pustych ramek
		for(int i = 0; i < lista.size(); i++){
			boolean czyRozna = czyJestRozna(lista.get(i).nr_strony, lista.get(i).nr_procesu);
			int obecnyProces = lista.get(i).nr_procesu-1;
			//System.out.println(id_ramki[obecnyProces]);
			if(czyRozna && Pamiec.lista_ramek.get(id_ramki[obecnyProces]).isPusta()){
				Pamiec.lista_ramek.get(id_ramki[obecnyProces]).wartosc = this.lista.get(i).nr_strony;
				Pamiec.lista_ramek.get(id_ramki[obecnyProces]).pusta = false;
				this.lista.get(i).bit_poprawnosci = false;
				brak_stron[obecnyProces]++;
				id_ramki[obecnyProces]++;
				if(id_ramki[obecnyProces] >= ramekDlaProcesu[obecnyProces]){
					id_ramki[obecnyProces] = znajdzNajdawniejszy(i);
				}
				
				
				//System.out.println("Jestem");
			}
			else if(czyRozna){
				if(sprawdzanieCzestosc && brak_stron[obecnyProces] > Proces.dopuszczalne[obecnyProces] && jestWolnaRamka()){
					dodajRamke(obecnyProces, this.lista.get(i).nr_strony);
				} else{
					for(int n = 0; n<this.lista.size(); n++){
						if(Pamiec.lista_ramek.get(id_ramki[obecnyProces]).wartosc == this.lista.get(n).nr_strony && this.lista.get(n).nadpisany == false){
							this.lista.get(n).nadpisany = true;
							break;
						}
					}
					Pamiec.lista_ramek.get(id_ramki[obecnyProces]).wartosc = this.lista.get(i).nr_strony;
				}
				
				brak_stron[obecnyProces]++;
				id_ramki[obecnyProces] = znajdzNajdawniejszy(i);
			}
			else{
				this.lista.get(i).bit_poprawnosci = true;
				for(int n = 0; n<this.lista.size(); n++){
					if(this.lista.get(n).nr_strony == this.lista.get(i).nr_strony && this.lista.get(n).nadpisany == false){
						this.lista.get(n).nadpisany = true;
						break;
					}
				}
			}
				
		}
		
	}
	
	public boolean czyJestRozna(int nr_strony, int proces){
		for(int i = 0; i < Pamiec.lista_ramek.size(); i++){
			if(Pamiec.lista_ramek.get(i).wartosc == nr_strony && Pamiec.lista_ramek.get(i).nr_procesu == proces){
				return false;
			}
		}
		return true;
	}
	
	public int znajdzNajdawniejszy(int k){
		int id = 0;
		int naj = Pamiec.lista_ramek.size();
		int odleglosc = 0;
		for(int l = 0; l < Pamiec.lista_ramek.size(); l++){
			odleglosc = 0;
			for(int j = 0; j < k; j++){
				if(Pamiec.lista_ramek.get(l).wartosc == this.lista.get(j).nr_strony && this.lista.get(j).nadpisany == false)
					break;
				
					odleglosc++;
				
			}
			if(odleglosc < naj){
				naj = odleglosc;
				id = l;
			}
		}
		return id;
	}
	
	public boolean jestWolnaRamka(){
		for(int i = 0; i < Pamiec.lista_ramek.size(); i++){
			if(Pamiec.lista_ramek.get(i).isPusta()){
				return true;
			}
		}
		return false;
	}
	
	public void dodajRamke(int proces, int nrStrony){
		for(int i = 0; i < Pamiec.lista_ramek.size(); i++){
			if(Pamiec.lista_ramek.get(i).isPusta()){
				Pamiec.lista_ramek.get(i).nr_procesu = proces;
				Pamiec.lista_ramek.get(i).pusta = false;
				Pamiec.lista_ramek.get(i).wartosc = nrStrony;
			}
		}
	}
	
	public int ileBrakow(int nr_procesu){
		return brak_stron[nr_procesu-1];
	}
}
