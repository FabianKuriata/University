import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Matrix
{
   int rows = 0;
   int columns = 0;
   int range = 0;
   double [][]tab;
   private static final String FILENAME = "C:\\Users\\Lenovo Y700\\Desktop\\plik.txt"; // miejsce zapisu
   public Matrix(int rows, int columns, int range)
   {
       this.rows = rows;
       this.columns = columns;
       this.range = range;
       tab = new double[rows][columns];
       for(int i=0; i<tab.length; i++)
       {
           for(int j=0; j<tab[i].length; j++)
           {
               tab[i][j] = (double)(Math.random()*range);
           }
       }
   }
   
   void display()
   {
       for(int i=0; i<rows; i++)
       {
           for(int j=0; j<columns; j++)
           {
               System.out.printf("%10.5f", tab[i][j]);
           }
           System.out.println();
       }
   }
   
   public void write() throws Exception
   {
       BufferedWriter bw = null;
       FileWriter fw = null;
       
       try{
           String content = "blablawdwa";
           fw = new FileWriter(FILENAME);
           bw = new BufferedWriter(fw);
           bw.write("Macierz");
           bw.newLine();
           bw.write("Liczba wierszy: " + rows);
           bw.newLine();
           bw.write("Liczba kolumn: " + columns);
           bw.newLine();
           for(int i = 0; i<rows; i++)
           {
               for(int j = 0; j<columns; j++)
               {
                   
                   //String number = Double.toString(tab[i][j]);
                   String number = String.format("%10.5f", tab[i][j]); // formatowanie tablicy w pliku

                   bw.write(number);
                   
               }
               bw.newLine();
           }
           
           
           System.out.println("Zapisano");
       } catch(IOException e){
           e.printStackTrace();
        } finally{
            
            try{
                if(bw != null)
                    bw.close();
                
                if(fw != null)
                    fw.close();
            } catch(IOException e2){
                e2.printStackTrace();
            }
        }
   }
   
   public void read() throws Exception
   {
       BufferedReader reader = null;
       try{
           File file = new File(FILENAME);
           reader = new BufferedReader(new FileReader(file));
           int nRow = 0;
           String[] stringNumbers;
           //double[][] tab2 = new double [rows][columns];
           String line;
           int i = 0;
           while((line = reader.readLine()) != null) 
           {
               
               int j = 0;
               double number = 0;
               nRow++;
               if(nRow > 3)
               {
                   stringNumbers = line.split("  ");
                   
                   //System.out.println(stringNumbers[1]);
                   for(int m=1; m<=columns;m++)
                   {
                       number = Double.parseDouble(stringNumbers[m]);
                       //System.out.print(stringNumbers[m]);
                       tab[i][j] = number;  // zapisuje do glownej macierzy
                       j+=1;
                   }
                   //System.out.println();
                   i++;
               }
           }
           System.out.println("Odczytano macierz");
       } catch(IOException e){
           e.printStackTrace();
        } finally{
            try{
                reader.close();
            } catch(IOException e2){
                e2.printStackTrace();
            }
        }
       /* 
        for(int k = 0; k<rows; k++)
           {
                for(int l=0; l<columns; l++)
                System.out.print(tab2[k][l] + " "); 
                
                System.out.println();
           }
       */
   }
   
   public double countAverage()
   {
       int quantity = rows*columns; // ilosc liczb
       double sum = 0;
       double average = 0;
       for(int i=0; i<rows; i++)
       {
           for(int j=0; j<columns; j++)
           {
               sum+=tab[i][j];
           }
       }
       if(quantity == 0)
       {
           throw new ArithmeticException("Brak liczb w macierzy");
       }
       else 
           average = sum/quantity;
       
       return average;
   }
   public static void main(String []args) throws Exception
   {
       Matrix matrix = new Matrix(10,10,100);
       matrix.display();
       matrix.write();
       System.out.println();
       matrix.read();
       matrix.display();
       System.out.println();
       System.out.println("Średnia liczb w macierzy: "+matrix.countAverage());
   }
}
