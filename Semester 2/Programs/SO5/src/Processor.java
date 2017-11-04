import java.util.ArrayList;
import java.util.List;


public class Processor {
	int number;
	int load; // obciążenie procesora
	List<Process> executingProcesses; // lista wykonywanych procesów
	
	public Processor(int number){
		load = 0;
		executingProcesses = new ArrayList<Process>();
		this.number = number;
	}
	
	public int currentLoad(){
		int currentLoad = 0;
		for(Process p : executingProcesses){
			if(p.executed == false){
				currentLoad += p.requiredResource;
			}
		}
		return currentLoad;
	}
	
	public void clear(){
		executingProcesses = new ArrayList<Process>();
	}
}
