import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static List<Process> generatedProcess = new ArrayList<Process>(); // Lista wygenerowanych procesów
	public static List<Processor> processors = new ArrayList<Processor>(); // Lista procesorów
	public static void main(String[] args){
		
		// Parametry do zmieniania
		int n = 5; // Liczba procesorów
		int p = 90; // Próg P - maksymalny
		int r = 10; // Próg R - minimalny
		int z = 10; // Ilość szukań
		
		int numberOfProcess = 1000; // Liczba wygenerowanych procesów
		
		
		
		Strategy1 strategy1;
		Strategy2 strategy2;
		Strategy3 strategy3;
		
		// Tworzenie procesorów
				for(int i = 0; i < n ; i ++){
					processors.add(new Processor(i+1));
				}
		
		// Generowanie procesów
		for(int i = 0; i < numberOfProcess; i++){
			generatedProcess.add(new Process());
		}
		// Sortowanie według czasu przyjścia
		Collections.sort(generatedProcess);
		
		// STRATEGIA 1 - szukaj innego procesora < "p"  "z" razy
		System.out.println("STRATEGIA 1");
		strategy1 = new Strategy1(p, z);
		strategy1.printAmountOfMigration();
		strategy1.printAmountOfQuestions();
		strategy1.printAverageDeviation();
		System.out.println();
		strategy1.printAverageLoad();
		
		// Czyszczenie
		clean();
		
		// STRATEGIA 2 - gdy obciążenie > "p" to szukaj innego procesora aż nie znajdziesz <"p"
		System.out.println("STRATEGIA 2");
		strategy2 = new Strategy2(p);
		strategy2.printAmountOfMigration();
		strategy2.printAmountOfQuestions();
		strategy2.printAverageDeviation();
		System.out.println();
		strategy2.printAverageLoad();
		
		clean();
		System.out.println();
		
		// STRATEGIA 3
		System.out.println("STRATEGIA 3");
		strategy3 = new Strategy3(p, r);
		strategy3.printAmountOfMigration();
		strategy3.printAmountOfQuestions();
		strategy3.printAverageDeviation();
		System.out.println();
		strategy3.printAverageLoad();
		
		//for(int i = 0 ; i < generatedProcess.size(); i++){
			//System.out.println(generatedProcess.get(i).timeToCome);
		//}
		
	}
	
	private static void clean(){
		for(int i = 0; i < processors.size() ; i ++){
			processors.get(i).clear();
		}
		
		for(int i = 0; i < generatedProcess.size(); i++){
			generatedProcess.get(i).belongTo = -1;
			generatedProcess.get(i).executed = false;
		}
	}
}
