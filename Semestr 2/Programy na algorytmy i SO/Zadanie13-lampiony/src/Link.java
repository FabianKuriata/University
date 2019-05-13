
public class Link {	
		int from = 0;
		int to = 0;
		int length = 0;
		
		Link(int from, int to, int length){
			this.from = from;
			this.to= to;
			this.length = length;
		}
		
		public String toString(){
			return from + " " + to + " " + length;
		}
}
