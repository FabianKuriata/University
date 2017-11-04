import java.util.Random;

public class Process implements Comparable<Process> {
	int requiredResource; // ile wymaga zasobu procesora w %
	int executionTime; // czas wykonania
	int belongTo; // proces należy do danego nr procesora, czyli jest wykonywany lub wykonany przez niego
	int timeToCome; // czas przyjścia procesu
	
	boolean executed; // czy wykonany
	
	Random random = new Random();
	public Process(){
		// Losowanie wartości
		requiredResource = random.nextInt(5)+1; 
		executionTime = random.nextInt(10000)+1;
		timeToCome = random.nextInt(10);
		// Wartości początkowe
		belongTo = -1;
		executed = false;
	}
	
	public Process(int r, int e, int t, int b){
		
		requiredResource = r; 
		executionTime = e;
		timeToCome = t;
		
		belongTo = b;
		executed = false;
	}
	
	@Override
	public int compareTo(Process arg0) {
		// TODO Auto-generated method stub
		
		return this.timeToCome - arg0.timeToCome;
	}
	
}
