
public class Ramka {
	boolean pusta;
	int wartosc;
	
	public Ramka(){
		pusta=true;
		wartosc = -1;
	}
	
	public String toString(){
		String wartoscS = Integer.toString(wartosc);
		return wartoscS;
	}
	
	public boolean isPusta() {
		return pusta;
	}

	public void setPusta(boolean pusta) {
		this.pusta = pusta;
	}

	public int getWartosc() {
		return wartosc;
	}

	public void setWartosc(int wartosc) {
		this.wartosc = wartosc;
	}
	
}
