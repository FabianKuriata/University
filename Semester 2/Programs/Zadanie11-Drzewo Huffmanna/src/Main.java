import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

	static List<Symbol> codes = new ArrayList<Symbol>();

	public static void main(String[] args) throws IOException {
		String sentence = "";

		sentence = readFile("sentence.txt");
		
		int[] charFreqs = new int[512];
		
		for (char c : sentence.toCharArray())
			charFreqs[c]++;

		HuffmanTree tree = HuffmanCode.buildTree(charFreqs);

		System.out.println("SYMBOL\tWAGA\tKOD");
		HuffmanCode.printCodes(tree, new StringBuffer());
		
		writeEncodedSentence("sentence-encoded.txt", sentence);
	}

	
	
	public static String readFile(String path) throws IOException {
		FileReader file = new FileReader(path);
		String sentence = "";
		BufferedReader bufferedReader = new BufferedReader(file);

		String textLine = bufferedReader.readLine();
		do {
			sentence += textLine;
			textLine = bufferedReader.readLine();

		} while (textLine != null);

		bufferedReader.close();

		return sentence;
	}

	public static void writeEncodedSentence(String path, String sentence) throws IOException {
		String sentenceEncoded = "";
		for (int j = 0; j < sentence.length(); j++) {
			for (int i = 0; i < codes.size(); i++) {
				if (sentence.charAt(j) == codes.get(i).symbol) {
					sentenceEncoded += codes.get(i).code;
					i = codes.size();
				}
			}
		}
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		System.out.println();
		try {
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(sentenceEncoded);
			System.out.println("Zapisano zakodowany tekst");
		} catch (IOException e) {
			e.printStackTrace();

		}finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}
}
