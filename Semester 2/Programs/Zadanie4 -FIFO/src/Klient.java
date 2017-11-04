
public class Klient {
	Kolejka products;
	String name;
	public Klient(String name){
		products = new Kolejka(name);
		this.name = name;
	}
	
	public void order(String name, int amount){
		products.insert(name, amount);
	}
	
	public String getName(){
		return name;
	}
}
