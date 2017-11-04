
public class Kolejka implements Queue {
	
	Element first;
	String owner;
	boolean full = false;
	public Kolejka(String owner){
		first = null;
		this.owner = owner;
		//product = null;
	}
	
	@Override
	public void insert(String name, int amount) {
		
		
		if (first==null) {
			first = new Element(name, amount); //jeœli lista jest pusta ustawiamy nastêpnik wartownika
		}
        else{
        	 Element last = first;
        	 while(last.getNext() != null) //szukamy ostatniego elementu
                 last=last.getNext();
             //++size;
             last.setNext(new Element(name, amount)); 
        }
       
		
	}
	
	@Override
	public boolean remove(String name) {
		
		if(first.getName().equals(name)){
			first = null;
			return true;
		}
		
		if(first.getNext().getName().equals(name)){
			first.setNext(first.getNext().getNext());
			return true;
		}
		
		Element remove = first.getNext();
		
		while(remove != null && remove.getNext() != null){
			if(remove.getNext().getName().equals(name)){
				remove.setNext(remove.getNext().getNext());
				return true;
			}
			remove = remove.getNext();
		}
		return false;
	}
	
	@Override
	public boolean isEmpty(){
		return first == null;
	}
	
	public boolean isFull(){
		return full;
	}
	
	public int size(){
		Element p = first;
		int size = 0;
		
		while(p != null){
			size++;
			p = p.next;
		}
		
		return size;
	}
	
	void display(){
		Element p = first;
		
		while(p != null){
			System.out.println(p.getName());
			p = p.next;
		}
		
	}
}
