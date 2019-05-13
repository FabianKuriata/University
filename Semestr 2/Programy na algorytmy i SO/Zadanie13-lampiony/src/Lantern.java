import java.util.ArrayList;
import java.util.List;

class Lantern implements Comparable<Lantern> {
	public final int id;
	public final String name;
	public List<Wire> wires;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Lantern previous;

	public Lantern(String argName, int id) {
		wires = new ArrayList<Wire>();
		name = argName;
		this.id = id;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Lantern other) {
		return Double.compare(minDistance, other.minDistance);
	}

}
