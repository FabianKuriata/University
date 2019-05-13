import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrindingData {
	int numberOfLanterns;
	int numberOfWires;
	List<Link> links;
	public GrindingData(String filename){
		links = new ArrayList<Link>();
		readFile(filename);
	}
	
	private void readFile(String filename){
		BufferedReader br = null;
		FileReader fr = null;
		int nrLine = 1;
		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String currentLine;

			br = new BufferedReader(new FileReader(filename));
			String split[];
			while ((currentLine = br.readLine()) != null) {
				if(nrLine == 1){
					
					split = currentLine.split(" ");
					numberOfLanterns = Integer.parseInt(split[0]);
					numberOfWires = Integer.parseInt(split[1]);
				} else{
					split = currentLine.split(" ");
					Link link = new Link(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
					links.add(link);
				}
				
				nrLine++;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
				
			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	
}
