import java.util.Random;

public class Data {
	int[] data;
	int[] tempData;
	int[] helper;
	int size;
	int n;
	int left;
	int right;
	int largest;
	
	public Data(int size){
		data = new int[size];
		tempData = new int[size];
		helper = new int[size];
		this.size = size;
	}
	
	public void fillNumbers(int przedzial, int kolejnosc){
		Random ran = new Random();
		if(kolejnosc == 1){
			for(int i = 0; i < size-1; i++){
				data[i] = ran.nextInt(przedzial);
				
			}
			insertSort();
			for(int i = 0; i < size-1; i++){
				tempData[i] = data[i];
				helper[i] = data[i];
			}
		}
		else if(kolejnosc == -1){
			for(int i = 0; i < size-1; i++){
				data[i] = ran.nextInt(przedzial);
				
			}
			insertSortD();
			for(int i = 0; i < size-1; i++){
				tempData[i] = data[i];
				helper[i] = data[i];
			}
			
		}
		else{
			for(int i = 0; i < size-1; i++){
				data[i] = ran.nextInt(przedzial);
				tempData[i] = data[i];
				helper[i] = data[i];
			}
		}
	}
	
	public void restore(){
		for(int i = 0; i < data.length-1; i++){
			this.data[i] = tempData[i];
		}
	}
	public void display(){
		for(int i = 0; i < data.length-1;i++){
			System.out.println(data[i]);
		}
	}
	
	//////// BUBBLE SORT ////////////
	public void bubbleSort(){
		 
		 
		 int j;
	     boolean flag = true;   
	     int temp;  

	     while ( flag )
	     {
	            flag= false;   
	            for( j=0;  j < data.length-1;  j++ )
	            {
	                   if ( data[ j ] > data[j+1] )  
	                   {
	                           temp = data[ j ];                
	                           data[ j ] = data[ j+1 ];
	                           data[ j+1 ] = temp;
	                          flag = true;              
	                  } 
	            } 
	      }
	    
	}
	
	public void bubbleSortA(){
		 
		 
		 int j;  
	     int temp;  

	    for(int i = 0; i < data.length; i++){
	    	for( j=0;  j < data.length-1-i;  j++ )
            {
                   if ( data[ j ] > data[j+1] )  
                   {
                           temp = data[ j ];                
                           data[ j ] = data[ j+1 ];
                           data[ j+1 ] = temp;                 
                  } 
            } 
	    }
	}         
	     
	    

	
	/////////// QUICK SORT  ///////////////
	public void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        
        int pivot = data[lowerIndex+(higherIndex-lowerIndex)/2];
        
        while (i <= j) {
          
            while (data[i] < pivot) {
                i++;
            }
            while (data[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                
                i++;
                j--;
            }
        }
       
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
       
	}
 
    private void exchangeNumbers(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    ///// INSERT SORT ///////
    public void insertSort()
    {
         int j;                   
         int i;  
         int key;
         for (j = 1; j < data.length; j++)    
        {
               key = data[ j ];
               for(i = j - 1; (i >= 0) && (data[ i ] > key); i--)   
              {
                     data[ i+1 ] = data[ i ];
              }
             data[ i+1 ] = key;    
         }
    }
    
    public void insertSortD()
    {
         int j;                    
         int key;               
         int i;  

         for (j = 1; j < data.length; j++)    // Start with 1 (not 0)
        {
               key = data[ j ];
               for(i = j - 1; (i >= 0) && (data[ i ] < key); i--)   // Smaller values are moving up
              {
                     data[ i+1 ] = data[ i ];
              }
             data[ i+1 ] = key;    // Put the key in its proper location
         }
    }
    /////////// SELECT SORT ////////////
    public void selectSort() {
        int i, j, minIndex, tmp;
        int n = data.length;
        for (i = 0; i < n - 1; i++) {
              minIndex = i;
              for (j = i + 1; j < n; j++)
                    if (data[j] < data[minIndex])
                          minIndex = j;
              if (minIndex != i) {
                    tmp = data[i];
                    data[i] = data[minIndex];
                    data[minIndex] = tmp;
              }
        }
    }
    
    ////////// MERGE SORT ///////////////
    public void mergeSort(int low, int high) {
            // check if low is smaller than high, if not then the array is sorted
            if (low < high) {
                    // Get the index of the element which is in the middle
                    int middle = low + (high - low) / 2;
                    // Sort the left side of the array
                    mergeSort(low, middle);
                    // Sort the right side of the array
                    mergeSort(middle + 1, high);
                    // Combine them both
                    merge(low, middle, high);
            }
    }

    private void merge(int low, int middle, int high) {

            // Copy both parts into the helper array
            for (int i = low; i <= high; i++) {
                    helper[i] = data[i];
            }

            int i = low;
            int j = middle + 1;
            int k = low;
            // Copy the smallest values from either the left or the right side back
            // to the original array
            while (i <= middle && j <= high) {
                    if (helper[i] <= helper[j]) {
                            data[k] = helper[i];
                            i++;
                    } else {
                            data[k] = helper[j];
                            j++;
                    }
                    k++;
            }
            // Copy the rest of the left side of the array into the target array
            while (i <= middle) {
                    data[k] = helper[i];
                    k++;
                    i++;
            }

    }
    
    /////// HEAP SORT /////////
    
    public void heapSort() {
        buildheap();
        for(int i=n; i>0; i--) {
           exchange(0, i);
           n=n-1;
           maxheap(data,0);
        }
     }
    
    public void buildheap() {
        n = data.length-1;
        for(int i=n/2; i>=0; i--){
           maxheap(data,i);
        }
     }
   
     public void maxheap(int[] a, int i) { 
        left = 2*i;
        right = 2*i+1;
   
        if(left <= n && a[left] > a[i]){
           largest=left;
        } else {
           largest=i;
        }
   
        if(right <= n && a[right] > a[largest]) {
           largest=right;
        }
        if(largest!=i) {
           exchange(i, largest);
           maxheap(a, largest);
        }
     }
   
     public void exchange(int i, int j) {
          int t = data[i];
          data[i] = data[j];
          data[j] = t; 
     }
}
