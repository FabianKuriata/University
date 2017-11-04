
public class Element {
	String name;
	int amount;
	
	Element next;
	
	public Element(String name, int amount){
		this.name = name;
		this.amount = amount;
		//next = el;
	}
	
	
	
	public Element(String name, Element el){
		this.name = name;
		next = el;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Element getNext() {
		return next;
	}

	public void setNext(Element next) {
		this.next = next;
	}
	
	
}
