
public class Word implements Comparable<Word> {
	int nrRow;
	String word;
	
	public Word(String word, int row){
		this.word = word;
		nrRow = row;
	}

	@Override
	public int compareTo(Word w) {
		int comparingWords = word.compareTo(w.word);
		return comparingWords;
	}
	
	public String toString(){
		return word;
	}
}
