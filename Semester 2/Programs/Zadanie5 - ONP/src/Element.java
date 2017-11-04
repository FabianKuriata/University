
public class Element {
	char value;
	String val;
	int priority;
	Element next;
	boolean openBracket = false;
	
	public Element(char v, Element el){
		value = v;
		next = el;
		setPriority(value);
	}
	
	public Element(String v, Element el){
		val = v;
		next = el;
		//setPriority();
	}

	public Element getNext() {
		return next;
	}

	public void setNext(Element next) {
		this.next = next;
	}
	
	public void setPriority(char sign){
		int p = 0;
		if(sign == '+' || sign == '-'){
			p = 1;
		}
		else if(sign == '*' || sign == '/'){
			p = 2;
		}
		else if(sign == '(' || sign == ')'){
			p = -1;
		}
		priority = p;
	}
	
	
	
}
