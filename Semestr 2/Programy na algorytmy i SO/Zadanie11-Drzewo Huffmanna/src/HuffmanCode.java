import java.util.PriorityQueue;

public class HuffmanCode {
		
	    public static HuffmanTree buildTree(int[] charFreqs) {
	        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
	        
	        for (int i = 0; i < charFreqs.length; i++)
	            if (charFreqs[i] > 0)
	                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));
	       
	    
	        while (trees.size() > 1) {
	            HuffmanTree a = trees.poll();
	            HuffmanTree b = trees.poll();
	 
	            trees.offer(new HuffmanNode(a, b));
	        }
	        return trees.poll();
	    }
	   
	    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
	        assert tree != null;
	        if (tree instanceof HuffmanLeaf) {
	            HuffmanLeaf leaf = (HuffmanLeaf)tree;
	 
	            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
	            Main.codes.add(new Symbol(leaf.value, prefix));
	        } else if (tree instanceof HuffmanNode) {
	            HuffmanNode node = (HuffmanNode)tree;
	 
	            prefix.append('0');
	            printCodes(node.left, prefix);
	            prefix.deleteCharAt(prefix.length()-1);
	 
	            prefix.append('1');
	            printCodes(node.right, prefix);
	            prefix.deleteCharAt(prefix.length()-1);
	        }
	    }
}