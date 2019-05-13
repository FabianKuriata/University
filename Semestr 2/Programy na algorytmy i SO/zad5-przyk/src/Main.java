import java.io.Console;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	@SuppressWarnings("rawtypes")
	static Stack stack = new Stack();
	 
	 
	/**
	* Main method
	* @param args
	*/
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	String result = "";
	String statement = "";
	boolean sign = true; //indicates that - is a number sign (not operator minus)
	 
	System.out.println("Podaj wyra¿enie:");
	Scanner scanner = new Scanner(System.in);
	statement = scanner.nextLine();
	//remove spaces from statement
	statement = statement.replaceAll(" ","");
	 
	//process all characters
	for (int i=0; i<statement.length(); i++) {
	if (statement.charAt(i) == '(') {
	stack.push("(");
	sign = true;
	result += " ";
	} else if (statement.charAt(i) == ')') {
	result += " " + getFromStackUntilBracket();
	sign = false;
	} else if ((statement.charAt(i) == '+' ||
	statement.charAt(i) == '-' ||
	statement.charAt(i) == '*' ||
	statement.charAt(i) == '/') && !sign) {
	result += " " + getFromStack(statement.substring(i, i+1));
	sign = true;
	} else {
	if (sign && statement.charAt(i) == '-') {
	result += " ";
	}
	result += statement.charAt(i);
	sign = false;              
	}
	}
	result += getAllFromStack();
	result = result.replaceAll("  ", " ");
	 
	System.out.println("ONP:" + result);
	}
	 
	/**
	* Gets all operators until bracket from stack
	* @return
	*/
	private static String getFromStackUntilBracket() {
	String result = "";
	String c = null;
	if (!stack.empty()) {
	c = (String) stack.pop();
	while (!c.equals("(")){
	result = result + " " + c;
	if (stack.empty()) break;
	c = (String) stack.pop();
	}
	}
	if (result.length() > 0) {
	result = " " + result;
	}
	return result;
	}
	 
	/**
	* Gets all operators with priority less or equal to oeprator from stack
	* @param operator
	* @return
	*/
	@SuppressWarnings("unchecked")
	private static String getFromStack(String operator) {
	String result = "";
	String c = null;
	if (!stack.empty()) {
	c = (String) stack.pop();
	while (((operator.equals("+") || operator.equals("-")) && !c.equals("(")) ||
	((operator.equals("/") || operator.equals("*")) && (c.equals("/") || c.equals("*")))){
	result += " " + c;
	if (stack.empty()) break;
	c = (String) stack.pop();
	}
	stack.push(c);
	}
	stack.push(operator);
	 
	return result;
	}
	 
	/**
	* Get all from stack
	* @return
	*/
	private static String getAllFromStack() {
	String result = "";
	String c = null;
	while (!stack.empty()){
	c = (String) stack.pop();
	result += " " + c;
	}
	return result;
	}  
	 
}

