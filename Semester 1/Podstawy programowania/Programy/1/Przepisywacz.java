import java.io.*;
import java.util.ArrayList;

/**
 * Created by KVD on 18.01.2017.
 */
public class Przepisywacz {
    public static void przepisz(String path) throws IOException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try{
            bufferedReader = new BufferedReader(
                    new FileReader(path)
            );
            bufferedWriter = new BufferedWriter(
                    new FileWriter(path+".txt")
            );
            bufferedWriter.write(bufferedReader.readLine());
            bufferedWriter.newLine();
            bufferedWriter.write(bufferedReader.readLine());
            bufferedWriter.write(" Wartosc");
            bufferedWriter.newLine();

            String line = bufferedReader.readLine();
            line.trim();
            while(line != null && !line.equals("")){
                String[] splited = line.split(" ");
                double ilosc = Double.parseDouble(splited[2]);
                double cena = Double.parseDouble(splited[3]);
                double wartosc = cena / ilosc;
                line += (" " + wartosc);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = bufferedReader.readLine();
                if(line != null){
                    line.trim();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie popsulo :( :( <smuteczek>");
        }
        finally {
            if(bufferedReader != null) bufferedReader.close();
            if(bufferedWriter != null) bufferedWriter.close();
        }
    }
    
    public static void main(String []args)
    {
        //Przepisywacz przepis = new Przepisywacz();
        String path = "C:\\Users\\Lenovo Y700\\Desktop\\bob.txt";
        przepisz(path);
    }
}
