
public class Etat {
	
	private double placa,limit;

	public Etat(boolean urzednik,double placa, double limit){

		if(limit <= 0 || placa <= 0 ){
			System.out.println("P³aca lub limit nie mog¹ byæ mniejsze lub równe zero.");
			throw new IllegalArgumentException();
		}
		
		this.limit = limit;
		this.placa = placa;
		
	}
	
	public double limitGodzin(){
		
		return limit;
		
	}

	public double placa(){
		
		return placa;
		
	}
	
}
