import java.util.ArrayList;
import java.util.Iterator;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card>();
	Card card;
	boolean end = false;

	public Deck(){
		while(!end){
			create();
			System.out.println("Dodano: " + card);
			if(card.value == 0){
				end = true;
			}
			else{
				Iterator<Card> it = deck.iterator();

				if(deck.size() > 1){
					Card current = it.next();
					System.out.println("Current:"+current.value);
					Card previous = null;
					Card next = it.next();
					System.out.println("Next: "+ next);
					int i = 0;
					while(it.hasNext()){
						i++;
						previous = current;
						current = it.next();
						System.out.println("Current w while:" + current);
						next = it.next();
						System.out.println("Next w while:" +next);
						if(current.value <= next.value && current.value >= previous.value){
							deck.add(i, current);
						}
					}
				}
				else
					deck.add(0, card);
			}
					
		}
		System.out.println("Utworzono talie "+deck.size()+" kart");
	}

	private void create() {
		card = new Card();
		
	}

	public void display() {
		Iterator<Card> itr = deck.iterator();
		for (Card temp : deck) {
			System.out.println(temp);
		}
	}

	public void amount() {
		System.out.println("Liczba kart: " + deck.size());
	}

	public void displayV(int v) {
		Iterator<Card> itr = deck.iterator();
		while (itr.hasNext()) {
			Card temp = itr.next();
			if (temp.getValue() == v)
				System.out.println(temp);
		}
	}

	public void displayC(int c) {
		Iterator<Card> itr = deck.iterator();

		for (; itr.hasNext();) {
			Card temp = itr.next();
			if (temp.getColor() == c)
				System.out.println(temp);
		}
	}
}
