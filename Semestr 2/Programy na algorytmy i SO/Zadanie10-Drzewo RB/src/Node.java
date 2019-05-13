
public class Node {
	private final int RED = 0;
	private final int BLACK = 1;
	
	
	Word key = null; 
	int color = BLACK;
    Node left = RBTree.nil, right = RBTree.nil, parent = RBTree.nil;

     Node(Word key) {
         this.key = key;
     } 
     
     public String toString(){
    	 return key.word;
     }
}
