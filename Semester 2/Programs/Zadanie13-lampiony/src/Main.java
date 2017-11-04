import java.util.List;
import java.util.ArrayList;

public class Main {
	static String info;

	public static void main(String[] args) {

		GrindingData grindingData = new GrindingData("da3"); // przemielenie danych z pliku
		

		// Tworzenie lampionów
		Lantern[] lanterns;
		lanterns = new Lantern[grindingData.numberOfLanterns];
		for (int i = 0; i < lanterns.length; i++) {
			String name = "Lampion ";
			name += Integer.toString(i + 1);
			lanterns[i] = new Lantern(name, i + 1);
			lanterns[i].wires.add(new Wire(lanterns[i], 0));
		}

		// Laczenie lampionow
		for (int i = 0; i < grindingData.numberOfWires; i++) {
			int indexFrom = grindingData.links.get(i).from - 1;
			int indexTo = grindingData.links.get(i).to - 1;
			int length = grindingData.links.get(i).length;

			Lantern to = lanterns[indexTo];
			Lantern from = lanterns[indexFrom];

			lanterns[indexFrom].wires.add(new Wire(to, length));
			lanterns[indexTo].wires.add(new Wire(from, length));
		}
		 Dijkstra.computePaths(lanterns[0]);
		 
		 
		 Dijkstra.computePaths(lanterns[0]);
		// Wyliczenie mozliwie najkrotszego kabla
		double minLength = 0;
		boolean[] connected = new boolean[lanterns.length];
		connected[0] = true;
		for (int i = 1; i < lanterns.length; i++) {
			// Szukanie najkrotszego kabla ze wszystkich polaczen
			double shortest = Double.POSITIVE_INFINITY;
			int indexShortest = 0;
			for(int k = 0; k < connected.length; k++){
				if(connected[k] == true && k != i){
					Dijkstra.computePaths(lanterns[k]);
					if(lanterns[i].minDistance < shortest){
						shortest = lanterns[i].minDistance; 
						indexShortest = k;
					}
				}
			}
			System.out.println(indexShortest);
			Dijkstra.computePaths(lanterns[indexShortest]);
			
			List<Lantern> path = Dijkstra.getShortestPathTo(lanterns[i]);
			System.out.println(path);
			if (path.size() > 2) {
				minLength += lanterns[i].minDistance;
				int index = indexShortest;
				for(int j = 1; j < path.size()-1; j++){
					//System.out.println(index);
					//minLength += lanterns[index].minDistance;
					index = path.get(j).id-1;
					//System.out.println(index);
					minLength -= lanterns[index].minDistance;
					
				}
				connected[i] = true;
			} else {
				minLength += lanterns[i].minDistance;
				connected[i] = true;
			}
		}
		 System.out.println("Możliwie najkrótszy kabel do podlaczenia " + grindingData.numberOfLanterns + " latarń :" + minLength + "cm");
		
	}

}
