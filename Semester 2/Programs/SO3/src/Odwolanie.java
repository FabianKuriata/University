
public class Odwolanie {
	int nr_strony;
	boolean bit_poprawnosci;
	boolean nadpisany;
	
	Odwolanie(){
		bit_poprawnosci = false;
		nadpisany = false;
	}
	
	public String toString(){
		String strona = Integer.toString(nr_strony);
		return strona;
	}
	public boolean isBit() {
		return bit_poprawnosci;
	}

	public void setBit(boolean bit_poprawnosci) {
		this.bit_poprawnosci = bit_poprawnosci;
	}
	
	
}
