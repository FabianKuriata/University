import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card>();
	Card card;
	int backsides = 0;
	int frontsides = 0;
	boolean end = false;
	
	public Deck(){
		while(!end){
			create();	
			if(card.value == 0){
				end = true;
			}
			else{
				
				if(deck.size()>=1){
					addSet(card);
				}
				else
					deck.add(card);
			}
			
					
		}
		System.out.println("Utworzono talie "+deck.size()+" kart");
	}
	
	private void create(){
		card = new Card();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addSet(Card c) {
		Card temp;
		Card temp2;
		//int indexC = deck.indexOf(c);
		
		for(int i = 0; i <= deck.size()-1; i++){
			if(deck.get(i).value > c.value){
				deck.add(i, c);
				i = deck.size();
			}
			else if(deck.get(i).value == c.value){
				if(deck.get(i).color > c.color){
					deck.add(i, c);
					i = deck.size();
				}
			}
		}
	}
	
	public void display(){
		
		for(Card temp: deck){
			System.out.println(temp);
		}
	}
	
	public void amount(){
		System.out.println("Liczba kart: "+deck.size());
	}
	
	public void displayV(int v){
		Iterator<Card> itr = deck.iterator();
		while(itr.hasNext()){
			Card temp = itr.next();
			if(temp.getValue() == v)
				System.out.println(temp);
		}
	}
	
	public void displayC(int c){
		Iterator<Card> itr = deck.iterator();
		
		for(;itr.hasNext();){
			Card temp = itr.next();
			if(temp.getColor() == c)
				System.out.println(temp);
		}
	}
	
	public void count(){
		Iterator<Card> itr = deck.iterator();
		while(itr.hasNext()){
			Card temp = itr.next();
			if(temp.hidden == true){
				backsides++;
			}
			else
				frontsides++;
		}
	}
	
	public void delete(int v){
		Iterator<Card> itr = deck.iterator();
		
		while(itr.hasNext()){
			Card temp;
			temp = itr.next();
			if(temp.value == v){
				itr.remove();
				System.out.println("Usunieto: "+ temp);
			}
		}
	}
}
