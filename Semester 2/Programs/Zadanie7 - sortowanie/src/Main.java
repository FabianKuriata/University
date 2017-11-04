
public class Main {
	public static void main(String[] args){
		Data data;
		data = new Data(100000);
		data.fillNumbers(40, -1);  // -1 - malejaco, 1 rosn¹co
		
		
		long czas = System.currentTimeMillis();
		data.quickSort(0, data.data.length-1);
		czas = System.currentTimeMillis() - czas;
		System.out.println("Czas quickSort:" + czas + "ms");
		data.restore();
		
		
		czas = System.currentTimeMillis();
		data.bubbleSort();
		
		czas = System.currentTimeMillis() -czas;
		System.out.println("Czas bubbleSort:" +czas +"ms");
		data.restore();
		
		czas = System.currentTimeMillis();
		data.bubbleSortA();
		//data.display();
		czas = System.currentTimeMillis() -czas;
		System.out.println("Czas bubbleSortA:" +czas +"ms");
		data.restore();
		//data.display();
		czas = System.currentTimeMillis();
		data.insertSort();
		czas = System.currentTimeMillis() - czas;
		System.out.println("Czas insertSort:" +czas +"ms");
		//data.display();
		data.restore();
		
		
		czas = System.currentTimeMillis();
		data.selectSort();
		czas = System.currentTimeMillis() - czas;
		System.out.println("Czas selectSort:" +czas +"ms");
		//data.display();
		data.restore();
		
		
		czas = System.currentTimeMillis();
		data.mergeSort(0, data.data.length-1);
		czas = System.currentTimeMillis() - czas;
		System.out.println("Czas mergeSort:" +czas +"ms");
		//data.display();
		data.restore();
		
		
		czas = System.currentTimeMillis();
		data.heapSort();
		czas = System.currentTimeMillis() - czas;
		System.out.println("Czas heapSort:" +czas +"ms");
		//data.display();
		data.restore();
		//data.display();
	}
	
}
