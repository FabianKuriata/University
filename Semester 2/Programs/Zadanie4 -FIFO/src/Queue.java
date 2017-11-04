
public interface Queue {
	public void insert(String name, int amount);
	//public void insert(Klient klient);
	public boolean remove(String name);
	public boolean isEmpty();
	public boolean isFull();
	
}
