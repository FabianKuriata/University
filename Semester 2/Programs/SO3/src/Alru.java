import java.util.ArrayList;
import java.util.List;

public class Alru{
	int brak_stron;
	int id_ramki;
	Pamiec pamiec;
	List<Odwolanie> lista;
	boolean czyRozna;
	
	public Alru(Pamiec pamiec, List<Odwolanie> lista2){
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
		
		for(int i = 0; i < this.lista.size(); i++){
			czyRozna = czyJestRozna(this.lista.get(i).nr_strony);
			//System.out.println("Czy rozna:" + czyRozna+ this.pamiec.lista_ramek);
			if(czyRozna && this.pamiec.lista_ramek.get(id_ramki).isPusta()){
				this.pamiec.lista_ramek.get(id_ramki).wartosc = this.lista.get(i).nr_strony;
				this.pamiec.lista_ramek.get(id_ramki).pusta = false;
				brak_stron++;
				id_ramki++;
				if(id_ramki >= this.pamiec.lista_ramek.size()){
					id_ramki = this.pamiec.lista_ramek.size()-1;
				}
				else{
					
				}
			}else if(czyRozna){
				
				this.pamiec.lista_ramek.get(id_ramki).wartosc = this.lista.get(i).nr_strony;
				brak_stron++;
			}else{
				//for(int n = 0; n < this.pamiec.lista_ramek.size(); n++){
				//	if(this.pamiec.lista_ramek.get(n).wartosc == this.lista.get(i).nr_strony){
				//		id_ramki = n;
				//		break;
				//	}
				//}
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
