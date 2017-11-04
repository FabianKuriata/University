import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Numbers {
	int []numbers;
	Random random = new Random();
	public Numbers(int n, int range){
		numbers = new int[n];
		for(int i = 0; i < n; i++){
			numbers[i] = random.nextInt(range);
		}
	}
	
	void shellSort1(List<Integer> tabH){
		for(int i = 0; tabH.get(i) > 1; i++){
			int h = tabH.get(i);
			for(int k = 0; k < h; k++){
				int[] temp = new int[numbers.length/h+1];
				int size = 0;
				for(int j = k, m = 0; j < numbers.length;m++){
					temp[m] = numbers[j];
					j += h;
					size++;
				}
				temp = Arrays.copyOf(temp, size);
				temp = insertSort(temp);
				//for(int p = 0; p < temp.length; p++){
				//	System.out.print(temp[p]+ "  ");
				//}
				//System.out.println();
				for(int j = k, m = 0; j < numbers.length;m++){
					if(temp[m] != 0){
						numbers[j] = temp[m];
					}
					j += h;
				}
			}
		}
		insertSort(numbers);
	}
	
	void shellSort2(List<Integer> tabH){
		for(int i = 0; tabH.get(i) > 1; i++){
			int h = tabH.get(i);
			for(int k = 0; k < h; k++){
				int[] temp = new int[numbers.length/h+1];
				int size = 0;
				for(int j = k, m = 0; j < numbers.length;m++){
					temp[m] = numbers[j];
					j += h;
					size++;
				}
				temp = Arrays.copyOf(temp, size);
				temp = insertSort(temp);
			
				for(int j = k, m = 0; j < numbers.length;m++){
					if(temp[m] != 0){
						numbers[j] = temp[m];
					}
					j += h;
				}
			}
		}
		bubbleSort(numbers);
	}
	
	void shellSort3(List<Integer> tabH){
		for(int i = 0; tabH.get(i) > 1; i++){
			int h = tabH.get(i);
			for(int k = 0; k < h; k++){
				int[] temp = new int[numbers.length/h+1];
				int size = 0;
				for(int j = k, m = 0; j < numbers.length;m++){
					temp[m] = numbers[j];
					j += h;
					size++;
				}
				temp = Arrays.copyOf(temp, size);
				temp = bubbleSort(temp);
				
				for(int j = k, m = 0; j < numbers.length;m++){
					if(temp[m] != 0){
						numbers[j] = temp[m];
					}
					j += h;
				}
			}
		}
		insertSort(numbers);
	}
	
	public int[] insertSort(int[] t){
		int j;                   
        int i;  
        int key;
        for (j = 1; j < t.length; j++)    
       {
              key = t[ j ];
              for(i = j - 1; (i >= 0) && (t[ i ] > key); i--)   
             {
                    t[ i+1 ] = t[ i ];
             }
            t[ i+1 ] = key;    
        }
        return t;
	}
	
	public int[] bubbleSort(int []t){
		 int j;  
	     int temp;  

	    for(int i = 0; i < t.length; i++){
	    	for( j=0;  j < t.length-1-i;  j++ )
            {
                   if ( t[ j ] > t[j+1] )  
                   {
                           temp = t[ j ];                
                           t[ j ] = t[ j+1 ];
                           t[ j+1 ] = temp;                 
                  } 
            } 
	    }
	    return t;
	}
	
	void display(){
		for(int i=0; i<numbers.length; i++){
			System.out.print(numbers[i]+ "  ");
		}
		System.out.println();
	}
}
