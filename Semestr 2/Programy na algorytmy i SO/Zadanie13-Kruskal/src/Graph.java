import java.util.*;


class Graph
{
   
    class Wire implements Comparable<Wire>
    {
        int src, dest;
        double length;
 
    
        public int compareTo(Wire compareWire)
        {
        	if(this.length < compareWire.length)
                return -1;
          else if(compareWire.length < this.length)
                return 1;
          return 0;
        }
    };
 
 
    class Subset
    {
        int parent, rank;
    };
 
    int L, W;    // L-> liczba lampionow  W->liczba kabli
    Wire wire[]; // zbior wszystkich kabli
 
    // Stworzenie grafu skladajacego sie z L wierzcholkow i W krawedzi
    Graph(int l, int w)
    {
        L = l;
        W = w;
        wire = new Wire[W];
        for (int i = 0; i < w; ++i)
            wire[i] = new Wire();
    }
 
   
    int find(Subset subsets[], int i)
    {
       
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
 
        return subsets[i].parent;
    }
 
    void Union(Subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
       
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
 
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
 
  
    void KruskalMST()
    {
        Wire result[] = new Wire[L]; 
        int e = 0;  // Indeks uzyty do result[]
        int i = 0;  // Indeks uzyty do 
        for (i=0; i<L; ++i)
            result[i] = new Wire();
 
        Arrays.sort(wire);
 
        Subset subsets[] = new Subset[L];
        for(i=0; i<L; ++i)
            subsets[i]=new Subset();
 
        for (int v = 0; v < L; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
 
        i = 0;
 
        while (e < L - 1)
        {
            
            Wire next_wire = new Wire();
            next_wire = wire[i++];
 
            int x = find(subsets, next_wire.src);
            int y = find(subsets, next_wire.dest);
 
            if (x != y)
            {
                result[e++] = next_wire;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }
 
       
        //System.out.println("Ścieżki:");
        double sum = 0;
        for (i = 0; i < e; ++i){
        	//System.out.println(result[i].src+" -- "+result[i].dest+" = "+ result[i].length);
        	sum += result[i].length;
        }
        System.out.println("Najkrótszy możliwie kabel potrzebny do połączenia " + L + " lampionów: " + sum + "cm");
            
    }
    
    
}