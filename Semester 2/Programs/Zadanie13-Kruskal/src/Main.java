
public class Main {
	public static void main (String[] args)
    {
 
		GrindingData grindingData = new GrindingData("da7");
        int L = grindingData.numberOfLanterns;  // Liczba wierzcholkow/lampionow w grafie
        int W = grindingData.numberOfWires;  // Liczba krawedzi/kabli w grafie
        
        Graph graph = new Graph(L, W);
        
        for(int i = 0; i < W; i++){
        	graph.wire[i].src = grindingData.links.get(i).from-1;
        	graph.wire[i].dest = grindingData.links.get(i).to-1;
        	graph.wire[i].length = grindingData.links.get(i).length;
        }
      
        graph.KruskalMST();
    }
}
