import java.util.EmptyStackException;

public class Stos implements Stack{
	
		Element first;
		int size;
		
		public Stos(){
			first = null;
			size = 0;
		}

		@Override
		public void push(char value) {
			if (first==null) {
				first = new Element(value, null);
				
			}
	        else{
	        	 Element old = first;
	        	 first = new Element(value,null);
	             first.next = old;   
	        }
			size++;
		}
		
		@Override
		public void push(String val) {
			if (first==null) {
				first = new Element(val, null);
				
			}
	        else{
	        	 Element old = first;
	        	 first = new Element(val,null);
	             first.next = old;   
	        }
			size++;
		}

		@Override
		public Element peek() {
			if(empty()) throw new EmptyStackException();
			
			return first;
		}

		@Override
		public Element pop() throws EmptyStackException {
			if(empty()) throw new EmptyStackException();
			Element removed = first;
			first = first.next;
			size--;
			return removed;
			
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int size() {
			Element f = first;
			size = 0;
			while(f != null){
				size++;
				f = f.next;
			}
			return size;
		}

		@Override
		public boolean empty() {
			// TODO Auto-generated method stub
			if(first == null){
				return true;
			}
			else
				return false;
		}
		
		@Override
		public void display(){
			Element f = first;
			while(f != null){
				System.out.println(f.value);
				f= f.next;
			}
		}
}
