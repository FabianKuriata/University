import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		InfixToPostfix tekst;
		String dzialanie1 = "((2+3)*2+2)%5";
		String dzialanie2 = "((10+2)*(2+3))/10";
		String dzialanie3 = "(2+2)/(2-2)";
		tekst = new InfixToPostfix();
		
		//Scanner scan = new Scanner(System.in);
		System.out.println("Dzia³anie 1: " + dzialanie1);
		//dzialanie = scan.nextLine();
		tekst.convert(dzialanie1);
		//tekst.stosOp.display();
		System.out.println("Zapis w ONP:" +tekst.postfix);
		System.out.println("Wynik :" + tekst.count(tekst.postfix));
		
		System.out.println();
		System.out.println("Dzia³anie 2: " + dzialanie2);
		tekst.convert(dzialanie2);
		//tekst.stosOp.display();
		System.out.println("Zapis w ONP:" +tekst.postfix);
		System.out.println("Wynik :" + tekst.count(tekst.postfix));
		
		System.out.println();
		System.out.println("Dzia³anie 3: " + dzialanie3);
		tekst.convert(dzialanie3);
		//tekst.stosOp.display();
		System.out.println("Zapis w ONP:" +tekst.postfix);
		if(tekst.count(tekst.postfix) != -1)
			System.out.println("Wynik :" + tekst.count(tekst.postfix));
		else{
			
		}
		if(czyZrownowazone("{(2+3)*2+[2+2]/2}}")){
			System.out.println("Zrownowa¿one dzia³anie");
		}
		else 
			System.out.println("Nie zrównowa¿one");
		///System.out.println(czyZrownowazone("{(2+3)*2+[2+2]/2}"));
	}
	
	
	public static boolean czyZrownowazone(String s){
		Stos nawiasy = new Stos();
		boolean closed = false;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){
				nawiasy.push(s.charAt(i));
				nawiasy.peek().openBracket = true;
			} else if(s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']'){
				char nawias = s.charAt(i);
				if(nawias == ')'){
					Element p = nawiasy.first;
					while(p != null){
						if(p.value == '(' && p.openBracket == true){
							p.openBracket = false;
							closed = true;
							break;	
						}
						else if(p.next == null)
							closed = false;
						p = p.next;
					}
					if(!closed)
						return false;
					
				}else if(nawias == '}'){
					
					Element p = nawiasy.first;
					while(p != null){
						if(p.value == '{' && p.openBracket == true){
							p.openBracket = false;
							closed = true;
							break;
							//nawiasy.pop();
							
						}
						else if(p.next == null)
							closed = false;
						p = p.next;
					}
					if(!closed)
						return false;
				}else if(nawias == ']'){
					Element p = nawiasy.first;
					while(p != null){
						if(p.value == '[' && p.openBracket == true){
							p.openBracket = false;
							closed = true;
							break;
							//nawiasy.pop();			
						}
						else if(p.next == null)
							closed = false;
						p = p.next;
					}
					if(!closed)
						return false;
				}
			}
				
		}
		
		Element p = nawiasy.first;
		
		while(p != null){
			
			if(p.openBracket == true){
				return false;
			}
			p = p.next;
		}
		
		return true;
	}
}
