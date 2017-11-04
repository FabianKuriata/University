import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Lista {
	
	private Element pierwszy;
	//Lista lista = new Lista();
	public Lista(){
		pierwszy = null;
		Card card = new Card();

			while(card.value != 0){

				System.out.println(card);
					dodaj(card);

				card = new Card();
			}
	}
	
	
	
	boolean pusta(){
		return pierwszy==null;
	}
	
	
	
	Element dodaj(Card c, Element e){
		Element el;
		
		if(pierwszy == null){
			el = new Element(c, null);
			pierwszy = el;
		}
		else{
			el = new Element(c, e.nastepnik);
			e.nastepnik = el;
		}
		
		return el;
	}
	
	public Card dodaj(Card card){
		
	    Element element = new Element();
	    element.karta = card;

	    if (pierwszy == null) {
	        pierwszy = element;
	        return card;
	    } else if (element.karta.value <= pierwszy.karta.value) {
	    	if(element.karta.value == pierwszy.karta.value){
	    		if(element.karta.color < pierwszy.karta.color){
	    			element.nastepnik = pierwszy;
	    			pierwszy = element;
	    			return card;
	    		}
	    	}
	    	else {
	    		element.nastepnik = pierwszy;
		        pierwszy = element;
		        return card;
	    	}
	       
	    } 
	    Element p = pierwszy;

	    boolean added=false;
	    while (p.nastepnik != null)
	    {
	        if (p.nastepnik.karta.value > card.value )
	        { 
	           element.nastepnik = p.nastepnik;
	           p.nastepnik = element;
	           added = true;
	           break;
	        }
	        else if(p.nastepnik.karta.value == card.value){
	        	if(p.nastepnik.karta.color > card.color){
	        		element.nastepnik = p.nastepnik;
	        		p.nastepnik = element;
	        		added = true;
	        		break;
	        	}
	        }
	        p = p.nastepnik;
	    }
	    if (!added) 
	        p.nastepnik = element;
	    
	    return card;
	}
	
	void usun(int c){
		
		int k = 0;
		while(k < liczbaElementow()){
			Element p = pierwszy;
			Element poprz = null;
			while (p != null && p.karta.color != c){
				poprz = p;
				p = p.nastepnik;
			}
			
			if (p != null){
				if(poprz == null){
					pierwszy = p.nastepnik;
				}
				else
					poprz.nastepnik = p.nastepnik;
			}
			
			k++;
		}
		
	}
	
	void szukajTyp(int v){
		Element p = pierwszy;
		
		while(p != null){
			if(p.karta.value != v){
				
			}
			else
				System.out.println(p.karta);
			
			p = p.nastepnik;
		}
		
	}
	
	void szukajKolor(int c){
		Element p = pierwszy;
		
		while(p != null){
			if(p.karta.color != c){
				
			}
			else
				System.out.println(p.karta);
			
			p = p.nastepnik;
		}
		
	}
	
	void wyswietl(){
		Element p = pierwszy;
		
		while(p != null){
			System.out.println(p.karta);
			p = p.nastepnik;
		}
	}
	
	int liczbaElementow(){
		Element p = pierwszy;
		int i = 0;
		while(p != null){
			i++;
			p = p.nastepnik;
		}
		return i;
	}
}



