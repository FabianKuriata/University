import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.text.*;
import java.util.Locale;

public class Matrix
{
   int rows = 0;
   int columns = 0;
   int range = 0;
   public double [][]tab;
   public double [][]tab2;
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
       BufferedReader reader2 = null;
       try{
           File file = new File(FILENAME);
           reader = new BufferedReader(new FileReader(file));
           reader2 = new BufferedReader(new FileReader(file));
           int columns2 = 0;
           int rows2 = 1;
           int nRow = 0;
           
           String[] stringNumbers;
        
           String line;
           /*/
           while((line = reader.readLine()) != null)
           {
               if(nRow>3)
               {
                   columns2 = 0;
                   String[] splited = line.split("  ");
                   
                   for(int i = 1; i<splited.length; i++)
                   {
                       columns2++;
                   }
                  
                   rows2++;
                }
                  
                   
               
               nRow++;
            }
            /*/
           
          
         
           int i = 0;
           nRow = 0;
           while((line = reader2.readLine()) != null) 
           {
               
               int j = 0;
               double num = 0;
               
               nRow++;
              if(nRow == 1)
              {
                  System.out.println(line);
              }
              if(nRow == 2)
              {
                   String[] splitted = line.split(" ");
                   rows2 = Integer.parseInt(splitted[2]);
                   System.out.println(line);
              }
              if(nRow == 3)
              {
                   String[] splitted = line.split(" ");
                   columns2 = Integer.parseInt(splitted[2]);
                   tab2 = new double [rows2][columns2];
                   System.out.println(line);
              }
              if(nRow > 3 && nRow <= rows2+3)
              {
                   stringNumbers = line.split("  ");
                   String numb ="";
                   
                   //System.out.println(rows2);
                   for(int m=1; m<=columns2;m++)
                   {
                       NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);                       
                       numb = stringNumbers[m].trim();
                       
                       Number number = format.parse(numb);
                       num = number.doubleValue();
                       //System.out.println(numb+"d");
                       tab2[i][j] = num;  
                       j+=1;
                   }
                   //System.out.println();
                   i++;
              }
               
               
           }
           for(int k = 0; k<rows2; k++)
               {
                    for(int l=0; l<columns2; l++)
                    System.out.printf("%10.5f",tab2[k][l]); 
                    
                    System.out.println();
               }
           System.out.println("Odczytano macierz");
       } catch(IOException e){
           e.printStackTrace();
        } finally{
            try{
                reader.close();
                reader2.close();
            } catch(IOException e2){
                e2.printStackTrace();
            }
        }
        
        
       
   }
   
   public double countAverage(double array[][])
   {
       int quantity = rows*columns; // ilosc liczb
       double sum = 0;
       double average = 0;
       for(int i=0; i<array.length; i++)
       {
           for(int j=0; j<array[i].length; j++)
           {
               sum+=array[i][j];
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
       Matrix matrix = new Matrix(5,8,100);
       matrix.display();
       //matrix.write();
       System.out.println();
       matrix.read();
       
       
       //matrix.display();
       System.out.println();
       System.out.println("Średnia liczb w macierzy po odczytaniu z pliku: "+matrix.countAverage(matrix.tab2));
       System.out.println("Średnia liczb w macierzy po zapisie nowej tablicy: "+matrix.countAverage(matrix.tab));
   }
}
