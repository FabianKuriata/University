
public class Ramka {
	boolean pusta;
	int wartosc;
	int nr_procesu;
	public Ramka(){
		pusta=true;
		nr_procesu = -1;
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
