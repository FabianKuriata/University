
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Strategy3 {
	int p;
	int r;
	List<Integer> randomNumbers;
	double[] averageLoadProcessor = new double[Main.processors.size()];
	double averageLoad;

	double averageDeviation; // średnie odchylenie

	int questionsAboutLoad; // ilość zapytań o obciążenie
	int migration; // ilość migracji/przemieszczen procesu

	public Strategy3(int p, int r) {
		this.p = p;
		this.r = r;

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

			prepareRandomNumbers();

			// Przydzielenie procesu nieobciązonemu procesorowi
			current = Main.generatedProcess.get(indexOfProcess);
			if (current.timeToCome <= time) {

				int x = random.nextInt(Main.processors.size());

				if (Main.processors.get(x).currentLoad() > p) {
					int y = x;
					while (y == x || Main.processors.get(y).currentLoad() > p) {
						y = random.nextInt(Main.processors.size());
						questionsAboutLoad++;
						time++;
						addLoads();
						doTheJob();
					}
					Main.processors.get(y).executingProcesses.add(new Process(current.requiredResource, current.executionTime, current.timeToCome, y));
					current.belongTo = y + 1;
					indexOfProcess++;
					migration++;

					// Przejecie zadan przeciazonego procesora przez procesor y
					randomNumbers.remove(y);
					if (Main.processors.get(y).currentLoad() < r) {

						while (!randomNumbers.isEmpty()) {
							//System.out.println(randomNumbers.size());
							int index = random.nextInt(randomNumbers.size());
							int nr = randomNumbers.get(index) + 1;
							int i = 0;
							questionsAboutLoad++;
							for (int k = 0; k < Main.processors.size(); k++) {
								if (Main.processors.get(k).number == nr) {
									break;
								}
								i++;
							}
							randomNumbers.remove(index);
							
							if (Main.processors.get(i).currentLoad() > p) {
								List<Process> processessMoved = new ArrayList<Process>();
								int totalRequired = 0;
								
								while (true) {
									Process isMove = Main.processors.get(i).executingProcesses.get(0);
									if (totalRequired + isMove.requiredResource <= 5) {
										isMove.belongTo = y+1;
										Main.processors.get(y).executingProcesses.add(isMove);
										Main.processors.get(i).executingProcesses.remove(0);
										totalRequired += isMove.requiredResource;
										migration++;
									} else {
										break;
									}
								}
							}

						}
					}
				} else {
					if (Main.processors.get(x).currentLoad() + current.requiredResource <= 100) {
						Main.processors.get(x).executingProcesses.add(
								new Process(current.requiredResource, current.executionTime, current.timeToCome, x));
						current.belongTo = x + 1;
						indexOfProcess++;

						// Przejecie zadan przeciazonego procesora przez procesor x
						if (Main.processors.get(x).currentLoad() < r) {
							randomNumbers.remove(x);
							
							while (!randomNumbers.isEmpty()) {
								
								int index = random.nextInt(randomNumbers.size());
								int nr = randomNumbers.get(index) + 1;
								int i = 0;

								for (int k = 0; k < Main.processors.size(); k++) {
									if (Main.processors.get(k).number == nr) {
										break;
									}
									i++;
								}
								
								randomNumbers.remove(index);

								if (Main.processors.get(i).currentLoad() > p) {
									
									int totalRequired = 0;
									questionsAboutLoad++;
									while (true) {
										Process isMove = Main.processors.get(i).executingProcesses.get(0);
										if (totalRequired + isMove.requiredResource <= 5) {
											isMove.belongTo = x+1;
											Main.processors.get(x).executingProcesses.add(isMove);
											Main.processors.get(i).executingProcesses.remove(0);
											totalRequired += isMove.requiredResource;
											migration++;
										} else {
											break;
										}
									}
								}

							}
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
			sum += deviation * deviation;
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

	public void printAverageDeviation() {
		System.out.print("Średnie odchlenie :");
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

	private void doTheJob() {
		for (int o = 0; o < Main.processors.size(); o++) {
			for (int m = 0; m < Main.processors.get(o).executingProcesses.size(); m++) {
				Main.processors.get(o).executingProcesses.get(m).executionTime -= 1;

				if (Main.processors.get(o).executingProcesses.get(m).executionTime <= 0) {

					// gdy executed = true, proces nie jest liczony do
					// obciazenia
					Main.processors.get(o).executingProcesses.get(m).executed = true;
				}
			}
		}
	}

	private void addLoads() {
		for (int i = 0; i < Main.processors.size(); i++) {
			averageLoadProcessor[i] += Main.processors.get(i).currentLoad();
		}
	}

	private void prepareRandomNumbers() {
		randomNumbers = new ArrayList<Integer>();
		for (int i = 0; i < Main.processors.size(); i++) {
			randomNumbers.add(i);
		}
	}
}
