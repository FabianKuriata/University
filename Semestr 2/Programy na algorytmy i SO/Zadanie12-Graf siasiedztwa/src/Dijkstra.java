import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	public static List<City> allPossibleDestination = new ArrayList<City>();
	public static void computePaths(City source) {
		source.minDistance = 0.;
		PriorityQueue<City> vertexQueue = new PriorityQueue<City>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			City u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Route e : u.routes) {
				City v = e.target;
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

	public static List<City> getShortestPathTo(City target) {
		List<City> path = new ArrayList<City>();
		for (City vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
		//List<Vertex> path = Dijkstra.getShortestPathTo(Wroclaw);
				//System.out.println("Path: " + path);
	}
	
	public static void printPossibleDestination(){
		for(City c : allPossibleDestination){
			System.out.println(c);
		}
	}
}