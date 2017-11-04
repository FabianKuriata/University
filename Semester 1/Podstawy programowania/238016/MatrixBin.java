import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class MatrixBin
{
   int rows = 0;
   int columns = 0;
   int range = 0;
   public double [][]tab;
   public double [][]tab2;
   private static final String FILENAME = "C:\\Users\\Lenovo Y700\\Desktop\\plik2.txt"; // miejsce zapisu
   public MatrixBin(int rows, int columns, int range)
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
       BufferedOutputStream bo = null;
       FileOutputStream fo = null;
       
       try{
           fo = new FileOutputStream(FILENAME);
           bo = new BufferedOutputStream(fo);
           String nCol = String.format("%4d", columns);
           String nRow = String.format("%4d", rows);
           String newLine = System.getProperty("line.separator");
           bo.write("Macierz".getBytes());bo.flush();
           bo.write(newLine.getBytes());
           bo.write(("Liczba wierszy: "+nRow).getBytes());
           bo.write(newLine.getBytes());
           bo.write(("Liczba kolumn: "+nCol).getBytes());
           bo.write(newLine.getBytes());
           
           for(int i = 0; i<rows; i++)
           {
                for(int j = 0; j<columns; j++)
                {
                    String bin = Long.toBinaryString(Double.doubleToRawLongBits(tab[i][j]));
                    String number = String.format("%100s", bin);
                    bo.write(number.getBytes());
                }
                bo.write(newLine.getBytes());
           }
            System.out.println("Zapisano");
       } catch(IOException e){
           e.printStackTrace();
        } finally{
            
            try{
                if( bo != null)
                {
                    bo.close();
                }
                if(fo != null)
                {  
                    fo.close();
                }
            } catch(IOException e2){
                e2.printStackTrace();
            }
        }
   }
   
   public void read() throws Exception
   {
       
       FileInputStream fin = null;
       BufferedInputStream reader = null;
       File file = null;
       try{
           file = new File(FILENAME);
           fin = new FileInputStream(file);
           reader = new BufferedInputStream(fin);
           
           byte[] text = new byte[1024*rows*columns];
           int bytesRead = 0;
           int position = 0;
           
           
           int rows2 = 0;
           int columns2 = 0;
           String strFileText;
         
           while((bytesRead = reader.read(text)) != -1)
           {
                strFileText = new String(text, position, 51);
                System.out.println(strFileText);
                position = 26;
                strFileText = new String(text, position, 5);
                strFileText = strFileText.trim();
                int num = Integer.parseInt(strFileText);
                rows2 = num;
                position = 46;
                strFileText = new String(text, position, 5);
                strFileText = strFileText.trim();
                num = Integer.parseInt(strFileText);
                columns2 = num;

                tab2 = new double[rows2][columns2];
                double number = 0;
                position = 70;
                for(int i=0; i<rows2; i++)
                {
                    for(int j=0; j<columns2; j++)
                    {
                        
                        strFileText = new String(text, position, 100);
                        strFileText = strFileText.trim();
                        
                        number = Double.longBitsToDouble(new BigInteger(strFileText,2).longValue());
                        tab2[i][j] = number;
                        position+=100;
                    }
                    position+=2;
                    //System.out.println();
                }
                //strFileText = new String(text, 170, 100);
                //System.out.print(rows2);
                //System.out.print(strFileText);
           }
           System.out.println();
           for(int i=0; i<rows2; i++)
           {
                for(int j=0; j<columns2; j++)
                {
                    System.out.printf("%10.5f", tab2[i][j]);
                }
                System.out.println();
           }
           System.out.println();
           System.out.println("Odczytano macierz");
       } catch(IOException e){
           e.printStackTrace();
        } finally{
            try{
                //reader2.close();
                reader.close();
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
       MatrixBin matrix = new MatrixBin(6,9,100);
       matrix.display();
       matrix.write();
       System.out.println();
       matrix.read();
       
       
       //matrix.display();
       System.out.println();
       System.out.println("Średnia liczb w macierzy po odczytaniu: "+matrix.countAverage(matrix.tab2));
       System.out.println("Średnia liczb w macierzy po zapisie: "+matrix.countAverage(matrix.tab));
   }
}
