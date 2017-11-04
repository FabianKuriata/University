import java.util.ArrayList;
import java.util.List;

public class UniqueWord {
	String uniqueWord;
	public List<Integer> isInRow;
	
	UniqueWord(String uniqueWord){
		this.isInRow = new ArrayList<Integer>();
		this.uniqueWord = uniqueWord;
	}
	
	public void isNotUnique(int row){
		this.isInRow.add(row);
	}
	
	public String toString(){
		return String.format("%-20s", uniqueWord);
	}
}
