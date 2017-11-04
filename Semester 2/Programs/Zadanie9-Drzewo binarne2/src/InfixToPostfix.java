
public class InfixToPostfix {
	Stos stosOp;
	String postfix;
	int i;

	public String convert(String infix) {
		stosOp = new Stos();
		postfix = new String();
		char sign;
		boolean operator = true;
		i = 0;
		while (i < infix.length()) {

			sign = infix.charAt(i);

			if (isOperator(sign)) {
				proccessOperator(sign);
				operator = true;
			} else {
				if(i+1 < infix.length()){
					if (operator && i != 0 && isOperator(infix.charAt(i+1))) {
						postfix +=sign + " ";
						
					}
					else if(isOperator(infix.charAt(i+1))){
						postfix += sign + " ";
						operator = false;
					}
					else{
						postfix +=sign;
						operator = false;
					}
				} else
					postfix += sign + " ";
				
			}
			i++;
		}
		Element f = stosOp.first;
		
		for (int i = 0; i < stosOp.size(); i++) {
			if(f.value != '(' && f.value != ')')
				postfix += f.value+ " ";
			f = f.next;
		}
		return postfix;
	}

	
	
	private void proccessOperator(char op)
    {
        if (stosOp.empty() || op=='(')//
            stosOp.push(op);
        else {
            Element topOp = stosOp.peek();
            if (precedence(op) > precedence(topOp.value)) {
                stosOp.push(op);
            } else {
                while (!stosOp.empty() && precedence(op) < precedence(topOp.value)) {
                    stosOp.pop();
                    if(topOp.value =='(' ){
                    	break;
                    }
                        
                    postfix+=topOp.value + " ";
                   if (!stosOp.empty()){
                	   topOp = stosOp.peek();
                	   if(topOp.value !=')' && precedence(op) < precedence(topOp.value))
                           stosOp.push(op);
                          
                   }
                        
                }
                    stosOp.push(op);
            }
        }
    }

	private int precedence(char op) {
		int p = 0;
		if (op == '+' || op == '-') {
			p = 1;
		} else if (op == '*' || op == '/' || op == '%') {
			p = 2;
		} else if (op == '(' || op == ')') {
			p = -1;
		}
		return p;
	}

	private boolean isOperator(char ch) {
		if (ch == '/' || ch == '*' || ch == '+' || ch == '-' || ch =='(' || ch == ')' || ch=='%') {
			return true;
		} else
			return false;
	}
	
	public int count(String pre){
		String temp = pre;
		Stos liczby = new Stos();
		String liczba = "";
		
		int wynik = 0;
		int temp2 = 0;
		int temp1;
		String sPoprz = null;
		String sObecny;
		String temp3;
		System.out.print(liczba);
		for(int i = 0; i < temp.length();){
			if(temp.charAt(i) != ' ' && !isOperator(temp.charAt(i))){
				liczba = "";
				while(temp.charAt(i) != ' ' && !isOperator(temp.charAt(i))){
					
					liczba += temp.charAt(i);
					i++;
				}
				liczby.push(liczba);
			}
			else if(isOperator(temp.charAt(i))){
				switch(temp.charAt(i)){
					case '+': {
							sPoprz = liczby.pop().val;
							sObecny = liczby.pop().val;
							temp2 = Integer.parseInt(sPoprz);
							temp1 = Integer.parseInt(sObecny);
							wynik = temp2 + temp1;
							temp3 = Integer.toString(wynik);
							liczby.push(temp3);		
		
						//liczba2 = wynik;
						break;
					}
					case '-': {
						temp2 = Integer.parseInt(liczby.pop().val);
						temp1 = Integer.parseInt(liczby.pop().val);
						wynik = temp1 - temp2;
						temp3 = Integer.toString(wynik);
						liczby.push(temp3);
						break;
					}
					case '*':{
						temp2 = Integer.parseInt(liczby.pop().val);
						temp1 = Integer.parseInt(liczby.pop().val);
						wynik = temp1 * temp2;
						temp3 = Integer.toString(wynik);
						liczby.push(temp3);
						break;
					}
					case '/':{
						try{
							temp2 = Integer.parseInt(liczby.pop().val);
							temp1 = Integer.parseInt(liczby.pop().val);
							wynik = temp1 / temp2;
							temp3 = Integer.toString(wynik);
							liczby.push(temp3);
						}catch(ArithmeticException e){
							if(temp2 == 0){
								System.out.println("Cholero nie dziel przez zero");
								return -1;
							}
						}
						break;
					}
					case '%':{
						temp2 = Integer.parseInt(liczby.pop().val);
						temp1 = Integer.parseInt(liczby.pop().val);
						wynik = temp1 % temp2;
						temp3 = Integer.toString(wynik);
						liczby.push(temp3);
						break;
					}
					default: System.out.println("B³êdny operator");
				}
				//wynik += liczba2 + liczba1;
				i++;
			}
			else
				i++;
		}
		return wynik;
		
	}
}
