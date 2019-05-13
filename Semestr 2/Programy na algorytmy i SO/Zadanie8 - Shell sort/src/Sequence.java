import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sequence {
	int number; // liczba elementow w ciagu
	String name;
	List<Integer> tab;
	
	public Sequence(){
		tab = new ArrayList<Integer>();
	}
	
	public List<Integer> a(int n){
		
		tab.add(1);
		int i = 0;
		while(tab.get(i).intValue() <= n){
			
			tab.add(3*tab.get(i)+1);
			
			if(3*tab.get(i+1).intValue()+1 >= n){
				break;
			}
			else{
				i++;
			}
		}
		Collections.reverse(tab);
		return tab;
	}
	
	public List<Integer> b(int n){
		
		int i = 0;
		tab.add(power(2,i+1)-1);
		while(tab.get(i).intValue() <= n){
			
			tab.add(power(2,i+2)-1);
			
			if(power(2,i+3)-1 >= n){
				break;
			}
			else{
				i++;
			}
		}
		Collections.reverse(tab);
		return tab;
	}
	
		public List<Integer> c(int n){
		
		int i = 0;
		tab.add(1);
		while(tab.get(i).intValue() <= n){
			
			tab.add(power(2,i+1)+1);
			
			if(power(2,i+2)+1 >= n){
				break;
			}
			else{
				i++;
			}
		}
		Collections.reverse(tab);
		return tab;
	}
	
	public int power(int number, int p){
		int result = 1;
		for(int i = 0; i < p; i++){
			result *= number;
		}
		return result;
	}
	
	public List<Integer> fibo(int n){
		tab.add(1);
		tab.add(1+1);
		for(int i=1; tab.get(i).intValue()<=n; i++){
			if(tab.get(i)+tab.get(i-1) >= n){
				break;
			}
			else{
				tab.add(tab.get(i)+tab.get(i-1));
			}
		}
		Collections.reverse(tab);
		return tab;
	}
	void display(){
		for(int a : tab){
			if(a != -1)
				System.out.print(a + "  ");
		}
	}
}
