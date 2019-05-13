import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
		List<Word> words = new ArrayList<Word>();
		RBTree rbt = new RBTree();
		readFile("example.txt", words);
		

		for(int k = 0; k < words.size(); k++){
			rbt.insert(new Node(words.get(k)));
		}
		
		rbt.printWords(rbt.root);
		rbt.breadth(rbt.root);
	}
	
	
	
	public static void readFile(String path, List<Word> listOfWords) throws IOException{
		FileReader file = new FileReader(path);
		
		BufferedReader bufferedReader = new BufferedReader(file);
		  
		String textLine = bufferedReader.readLine();
		String[] separated;
		int numberOfRow = 0;  
		do {
			separated = textLine.replaceAll("\\p{P}", "").toLowerCase().split("\\s+");
			numberOfRow++;
			for(int i = 0; i < separated.length; i++){
					listOfWords.add(new Word(separated[i], numberOfRow));
			}
		    
		    textLine = bufferedReader.readLine();
		    
		  } while(textLine != null);

		  bufferedReader.close();
	}

}

