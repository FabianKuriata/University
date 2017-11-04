import java.util.ArrayList;
import java.util.List;

public class Magazyn{
	
	public List<Kolejka> fifo;
	Klient customer;
	int ilosc;
	int index;
	boolean full;
	int ileKlientow;
	int nr_magazynu = 0;
	
	public Magazyn(int nr) {
		fifo = new ArrayList<Kolejka>();
		index = 0;
		nr_magazynu = nr;
	}
	
	public void customerRequest(Klient customer, Kolejka products){
		if(!full){
			ilosc = 0;
			ileKlientow++;
			fifo.add(customer.products);
			fifo.get(index).owner = customer.getName();
			index++;	
		}
		else 
			System.out.println("Magazyn jest pe³ny");
	}
	
	public void doIt(){
		Element p = fifo.get(0).first;
		while(p != null){
			ilosc += p.amount;
				p = p.next;
		}
		
		
		System.out.println(fifo.get(0).owner + " zlecienie zrealizowane" + "\nZamówiono " + ilosc + " produktów");
		fifo.remove(0);
		index--;
		//ileKlientow--;
		
		
	}
	public void display(){
		for(Kolejka temp: fifo){
			System.out.println(temp.owner + " zamówi³: ");
			temp.display();
			System.out.println();
		}
	}
}
