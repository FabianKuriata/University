

@SuppressWarnings("hiding")
public class Element {
	Card karta;
	Element nastepnik;
	
	//Card element;
	
	public Element(){
		
	}
	
	public Element(Card c){
		c = null;
	}
	
	public Element(Card c, Element n){
		karta = c;
		nastepnik = n;
	}
	
	public Card getKarta(){
		return karta;
	}
	
	public Element getNastepnik(){
		return nastepnik;
	}
	
	public void setNastepnik(Element n){
		nastepnik = n;
	}
	
	public void setKarta(Card c){
		karta = c;
	}
}
