import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	public static List<Lantern> allPossibleDestination = new ArrayList<Lantern>();
	public static void computePaths(Lantern source) {
		source.minDistance = 0.;
		PriorityQueue<Lantern> vertexQueue = new PriorityQueue<Lantern>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Lantern u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Wire e : u.wires) {
				Lantern v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					allPossibleDestination.add(v);
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Lantern> getShortestPathTo(Lantern target) {
		List<Lantern> path = new ArrayList<Lantern>();
		for (Lantern vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
		
	}
	
	public void clean(){
		
	}
	
	public static void printPossibleDestination(){
		for(Lantern c : allPossibleDestination){
			System.out.println(c);
		}
	}
}