import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BinaryTree {
	Node root;
	Stack<Node> stack = new Stack<Node>();
	Queue<Node> queue = new LinkedList<Node>();
	String exp = "";
	int result = 0;
	public BinaryTree(){
		root = null;
	}
	
	
	public void insertToTree(String exp){
		
		String elementsOfExp[] = exp.split(" "); // Dzielenie wyra¿enia na czesci, aby wyroznic znaki i liczby
		for(int i = 0; i < elementsOfExp.length; i++){
			stack.push(new Node(elementsOfExp[i]));
		}
		root = stack.pop();
		int s = stack.size();
		
		Node focusNode = root;
		Node parent = focusNode;
		
		while(true){
			if(stack.peek().parent){
				if(parent.right == null){
					parent.right = stack.pop();
					parent.right.previous = parent;
					parent = parent.right;
				}
				else if(parent.left == null){
					parent.left = stack.pop();
					parent.left.previous = parent;
					parent = parent.left;
				}
				else
					parent = parent.previous;
			}
			else if(stack.peek().leaf){
				if(parent.right == null){
					parent.right = stack.pop();
					parent.right.previous = parent;
				}
				else if(parent.left == null){
					parent.left = stack.pop();
					parent.left.previous = parent;
				}
				else
					parent = parent.previous;
			}
			//System.out.println(parent);
			if(stack.isEmpty()){
				return;
			}
		}
		
	}
	
	public void inOrder(Node focusNode){
		if (focusNode != null) {
			 this.exp +="(";
			 System.out.print("(");
			 inOrder(focusNode.left);
			 this.exp += focusNode.sign + " ";
			 if(focusNode.sign.equals("6")){
				 this.result += Integer.parseInt(focusNode.sign);
			 }
			 System.out.print(focusNode);
			 inOrder(focusNode.right);
			 System.out.print(")");
			 this.exp +=")";
		}
	}
	
	public void postOrder(Node focusNode){
		if (focusNode != null) {
			 postOrder(focusNode.left);	 
			 postOrder(focusNode.right);
			 this.exp += focusNode.sign + " ";
			 System.out.print(focusNode);
		 }
	}
	
	public void preOrder(Node focusNode){
		if (focusNode != null) {
			 System.out.print(focusNode);
			 preOrder(focusNode.left);	 
			 preOrder(focusNode.right);
		 }
	}
	
	public int countInorder(String exp) throws ScriptException{
		
		 ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    
		    result = (int) engine.eval(exp);
		    return result;
	}
	
	public int countLeaves(Node focusNode){
		  if( focusNode == null )
		    return 0;
		  if( focusNode.left == null && focusNode.right == null ) {
		    return 1;
		  } else {
		    return countLeaves(focusNode.left) + countLeaves(focusNode.right);
		  }
	}
	
	public int height(Node focusNode){
		if(focusNode == null)
	        return -1;
	   else
	        return Math.max(height(focusNode.left), height(focusNode.right)) + 1;
	}
	
	public int countNodes(Node focusNode){
		  if( focusNode == null )
			    return 0;
	      else {
			    return countNodes(focusNode.left) + countNodes(focusNode.right) + 1;
			  }
	}
	
	void clearExp(){
		this.exp = "";
	}

}
