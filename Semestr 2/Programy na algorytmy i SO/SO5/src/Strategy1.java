
import java.util.Random;

public class Strategy1 {
	int p;
	int z;

	double[] averageLoadProcessor = new double[Main.processors.size()];
	double averageLoad;

	double averageDeviation; // średnie odchylenie

	int questionsAboutLoad; // ilość zapytań o obciążenie
	int migration; // ilość migracji/przemieszczen procesu

	public Strategy1(int p, int z) {
		this.p = p;
		this.z = z;
		doRequests();
	}

	private void doRequests() {
		int time = 0;
		int indexOfProcess = 0;
		Random random = new Random();
		Process current = null;
		while (stillNotHandled()) {

			// Wykonywanie przydzielonych procesów
			doTheJob();

			// Przydzielenie procesu nieobciązonemu procesorowi
			current = Main.generatedProcess.get(indexOfProcess);
			if (current.timeToCome <= time) {

				int x = random.nextInt(Main.processors.size());

				for (int i = 0; i < z; i++) {
					int y = x;
					while (y == x) {
						y = random.nextInt(Main.processors.size());
					}
					questionsAboutLoad++;
					time++;
					addLoads();
					doTheJob();
					//
					if (Main.processors.get(y).currentLoad() < p) {
						Main.processors.get(y).executingProcesses.add(new Process(current.requiredResource, current.executionTime, current.timeToCome, y));
						current.belongTo = y + 1;
						// Main.processors.get(y).load +=
						// current.requiredResource;
						indexOfProcess++;
						migration++;
						break;
					}

					// Gdy każdy z losowanych procesorów jest obciążony
					if (i + 1 == z) {
						if (Main.processors.get(x).currentLoad() + current.requiredResource <= 100) {
							Main.processors.get(x).executingProcesses.add(new Process(current.requiredResource, current.executionTime, current.timeToCome, x));
							current.belongTo = x+1;
							indexOfProcess++;
						}

					}
				}
			}

			time++;

			// Dodawanie obciazen aby wyliczyc srednia
			addLoads();
		}

		// Średnia obciażenia każdego procesora
		for (int i = 0; i < Main.processors.size(); i++) {
			averageLoadProcessor[i] = averageLoadProcessor[i] / time;
		}

		// Średnia obciążenia ogólna
		double sum = 0;
		for (int i = 0; i < Main.processors.size(); i++) {
			sum += averageLoadProcessor[i];
		}
		averageLoad = sum / Main.processors.size();

		// Średnie odchylenie
		sum = 0;
		for (int i = 0; i < Main.processors.size(); i++) {
			double deviation = averageLoad - averageLoadProcessor[i];
			sum += deviation*deviation;
		}
		averageDeviation = Math.sqrt(sum / Main.processors.size());
	}

	
	
	public void printAmountOfMigration() {
		System.out.println("Liczba migracji: " + migration);
	}

	public void printAmountOfQuestions() {
		System.out.println("Liczba zapytań: " + questionsAboutLoad);
	}

	public void printAverageLoad() {
		for (int i = 0; i < Main.processors.size(); i++) {
			System.out.print("Średnie obciązenia " + (i + 1) + " procesora: ");
			System.out.printf("%.2f", averageLoadProcessor[i]);
			System.out.println(" %");
		}
		System.out.println("-------------------------------------");
		System.out.print("Średnie obciążenie procesorów: ");
		System.out.printf("%.2f", averageLoad);
		System.out.println(" %");
	}
	
	public void printAverageDeviation(){
		System.out.print("Średnie odchylenie :");
		System.out.printf("%.2f", averageDeviation);
		System.out.println(" %");
	}
	
	private boolean stillNotHandled() {
		for (int i = 0; i < Main.generatedProcess.size(); i++) {
			if (Main.generatedProcess.get(i).executed == false && Main.generatedProcess.get(i).belongTo == -1) {
				return true;
			}
		}
		return false;
	}
	
	private void doTheJob(){
		for (int o = 0; o < Main.processors.size(); o++) {
			for (int m = 0; m < Main.processors.get(o).executingProcesses.size(); m++) {
				Main.processors.get(o).executingProcesses.get(m).executionTime -= 1;

				if (Main.processors.get(o).executingProcesses.get(m).executionTime <= 0) {
					
				//gdy executed = true, proces nie jest liczony do obciazenia
					Main.processors.get(o).executingProcesses.get(m).executed = true; 
				}
			}
		}
	}
	
	private void addLoads(){
		for (int i = 0; i < Main.processors.size(); i++) {
			averageLoadProcessor[i] += Main.processors.get(i).currentLoad();
		}
	}
}
