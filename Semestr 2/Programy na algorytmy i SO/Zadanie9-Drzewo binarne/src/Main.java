import java.util.Stack;
public class Main {
	public static void main(String[] args){
		InfixToPostfix tekst;
		String dzialanie1 = "2+(2*6)";
		
		tekst = new InfixToPostfix();	
		
		System.out.println("Dzia³anie 1: " + dzialanie1);
		tekst.convert(dzialanie1);
		
		System.out.println("Zapis w ONP:" +tekst.postfix);
		System.out.println("Wynik :" + tekst.count(tekst.postfix));
		BinaryTree tree = new BinaryTree();
		tree.buildTree(tekst.convert(dzialanie1));

	}
	
	
	
}
