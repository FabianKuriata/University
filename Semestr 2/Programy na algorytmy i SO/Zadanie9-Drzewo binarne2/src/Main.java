import javax.script.ScriptException;

public class Main {
	public static void main(String[] args) throws ScriptException{
		String expression = "1+32*2";
		InfixToPostfix postfix = new InfixToPostfix();
		System.out.println(postfix.convert(expression));
		String inOrder = "";
		String postOrder = "";
		BinaryTree tree = new BinaryTree();
		tree.insertToTree(postfix.convert(expression));
		//System.out.println(tree.root.right.left.right);
		tree.inOrder(tree.root);
		inOrder = tree.exp;
		System.out.println(" = " + tree.countInorder(inOrder));
		System.out.println();
		tree.clearExp();
		tree.postOrder(tree.root);
		postOrder = tree.exp;
		System.out.print("= "+postfix.count(postOrder));
		System.out.println();
		System.out.println();
		System.out.println("Liczba liœci: " + tree.countLeaves(tree.root));
		System.out.println("Liczba wêz³ów: " + tree.countNodes(tree.root));
		System.out.println("Wysokoœæ: " + tree.height(tree.root));
	}
}

