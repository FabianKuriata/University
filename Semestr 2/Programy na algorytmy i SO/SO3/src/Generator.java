import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
	List<Odwolanie> lista_odwolan;

	public Generator(int dlugosc, int ilosc_stron){
		Random random = new Random();
		lista_odwolan = new ArrayList<Odwolanie>();

		for(int i = 0; i < dlugosc; i++){
			Odwolanie o = new Odwolanie();
			o.nr_strony = random.nextInt(ilosc_stron)+1;
			lista_odwolan.add(o);
		}
	}
	
	void display(){
		for(Odwolanie o : lista_odwolan){
			System.out.print(o + "  ");
		}
		System.out.println();
	}
	
}
