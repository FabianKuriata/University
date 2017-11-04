import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Kontener {
	
	Generator generuj = null;
	static List<Proces> lista_procesow = new ArrayList<Proces>();
	
	static double sredniaFcfs = 0;
	static double sredniaSjfb = 0;
	static double sredniaSjfw = 0;
	static double sredniaRot = 0;
	
	// sumy srednich
	static double suma_Fcfs = 0;
	static double suma_Sjfb = 0;
	static double suma_Sjfw = 0;
	static double suma_Rot = 0;
	
	public Kontener(){
		Generator.num = 0;
		
	}
	// liczba procesow
	void stworz(int liczba_procesow){
		
		for(int i = 0; i<liczba_procesow; i++)
		{
			// zapelnienie listy procesami
			generuj = new Generator();
			lista_procesow.add(generuj.proces);
		}
		
	}
	
	// wybor algorytmu
	void wybierz(Class a) throws InstantiationException, IllegalAccessException{
		a.newInstance();		
	}
	
	void wyswietl(){
		System.out.printf("%5s","ID");
		System.out.printf("%5s","DP");
		System.out.printf("%5s","MZ");
		System.out.printf("%4s","CO");
		System.out.println();
		for(int i=0; i<lista_procesow.size(); i++)
		{
			lista_procesow.get(i).numer = i+1;
			System.out.printf("%4d ",lista_procesow.get(i).numer);
			System.out.printf("%4d ",lista_procesow.get(i).dlugosc_fazy);
			System.out.printf("%4d ",lista_procesow.get(i).moment_zgloszenia);
			System.out.printf("%4d ",lista_procesow.get(i).czas_oczekiwania);
			System.out.println();
			
		}
	}
}

