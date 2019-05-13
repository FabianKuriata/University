
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.chart.ChartUtilities;

public class Main {
	public static void main(String[] args) throws IOException{
		int[] cases = new int[4];
		cases[0] = 5000;
		cases[1] = 10000;
		cases[2] = 50000;
		cases[3] = 100000;
		int range = 20;
		
		/////////////// TEST /////////////////////////
		for(int amount = 0; amount < cases.length; amount++){
			Sequence a;
			a = new Sequence();
			Sequence b;
			b = new Sequence();
			Sequence c;
			c = new Sequence();
			Sequence fibo;
			fibo = new Sequence();
			Numbers numbers;
			numbers = new Numbers(cases[amount],range);
			List<Long> clocks = new ArrayList<Long>();
			a.a(numbers.numbers.length);
			b.b(numbers.numbers.length);
			c.c(numbers.numbers.length);
			fibo.fibo(numbers.numbers.length);

			//a.display();
			//System.out.println();
			//b.display();
			//System.out.println();
			//c.display();
			//System.out.println();
			//fibo.display();
			//System.out.println();
			//System.out.println();
			//numbers.display();
			//numbers.shellSort1(a.tab);
			System.out.println();
			System.out.println();
			System.out.println("Przypadek dla "+ cases[amount] +" elementów");
			long czas = System.currentTimeMillis();
			numbers.shellSort1(a.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort1(b.tab);
			clocks.add(System.currentTimeMillis() - czas);
			czas = System.currentTimeMillis();
			numbers.shellSort1(c.tab);
			clocks.add(System.currentTimeMillis() - czas);
			czas = System.currentTimeMillis();
			numbers.shellSort1(fibo.tab);
			clocks.add(System.currentTimeMillis() - czas);
			//System.out.println(System.currentTimeMillis()-czas);
			
			czas = System.currentTimeMillis();
			numbers.shellSort2(a.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort2(b.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort2(c.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort2(fibo.tab);
			clocks.add(System.currentTimeMillis()- czas);
			//System.out.println(System.currentTimeMillis()-czas);
			
			czas = System.currentTimeMillis();
			numbers.shellSort3(a.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort3(b.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort3(c.tab);
			clocks.add(System.currentTimeMillis()- czas);
			czas = System.currentTimeMillis();
			numbers.shellSort3(fibo.tab);
			clocks.add(System.currentTimeMillis()- czas);
			
			//System.out.println(System.currentTimeMillis() - czas);
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			dataset.addValue(clocks.get(0), "Wersja 1", "A");
			dataset.addValue(clocks.get(1), "Wersja 1", "B");
			dataset.addValue(clocks.get(2), "Wersja 1", "C");
			dataset.addValue(clocks.get(3), "Wersja 1", "Fibonacci");
			
			dataset.addValue(clocks.get(4), "Wersja 2", "A");
			dataset.addValue(clocks.get(5), "Wersja 2", "B");
			dataset.addValue(clocks.get(6), "Wersja 2", "C");
			dataset.addValue(clocks.get(7), "Wersja 2", "Fibonacci");
			
			dataset.addValue(clocks.get(8), "Wersja 3", "A");
			dataset.addValue(clocks.get(9), "Wersja 3", "B");
			dataset.addValue(clocks.get(10), "Wersja 3", "C");
			dataset.addValue(clocks.get(11), "Wersja 3", "Fibonacci");
			
			JFreeChart bar = ChartFactory.createBarChart3D(
					"ShellSort w ró¿nych wersjach dla "+ cases[amount]+" elementów", 
					"Ci¹g", 
					"ms",
					dataset, 
					PlotOrientation.VERTICAL, 
					true,true,false);
			int width = 640;
			int height = 480;
			File barChart3D = new File("barChart3D"+amount+".jpeg");
			ChartUtilities.saveChartAsJPEG( barChart3D, bar, width, height);
			//BarChartDemo1 bar = new BarChartDemo1("Czas");
			
			for(int i = 0; i < clocks.size(); i++){
				if(i == 0){
					
					System.out.println("Wersja 1 ");
					System.out.printf("%10s %10s %10s %10s %n", "Ci¹g A", "Ci¹g B", "Ci¹g C", "Fibonacci");
					
				}else if(i == 4){
					System.out.println();
					System.out.println();
					System.out.println("Wersja 2 ");
					System.out.printf("%10s %10s %10s %10s %n", "Ci¹g A", "Ci¹g B", "Ci¹g C", "Fibonacci");
				}	
				else if (i == 8){
					System.out.println();
					System.out.println();
					System.out.println("Wersja 3 ");
					System.out.printf("%10s %10s %10s %10s %n", "Ci¹g A", "Ci¹g B", "Ci¹g C", "Fibonacci");
				}
				System.out.printf("%8d ", clocks.get(i));System.out.print("ms");
			}
		}
		
		
	}
}
