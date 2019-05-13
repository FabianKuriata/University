import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	int discSize = 500;

        ArrayList<Order> orderList = new ArrayList<>();
        initList(orderList, discSize);
        
        FCFS fcfs = new FCFS(orderList, discSize);
        System.out.println("Wynik dla FCFS " + fcfs.getDisplacement());

        SSTF sstf = new SSTF(orderList, discSize);
        System.out.println("Wynik dla SSTF " + sstf.getDisplacement());

        SCAN scan = new SCAN(orderList, discSize);
        System.out.println("Wynik dla SCAN " + scan.getDisplacement());

        CSCAN cscan = new CSCAN(orderList, discSize);
        System.out.println("Wynik dla CSCAN " + cscan.getDisplacement());

        EDF edf = new EDF(orderList, discSize);
        System.out.println("Wynik dla EDF " + edf.getDisplacement());

        FD_SCAN fd_scan = new FD_SCAN(orderList, discSize);
        System.out.println("Wynik dla FD-SCAN " + fd_scan.getDisplacement());
    }

    private static void initList(ArrayList<Order> orderList, int discSize) {
        Random random = new Random();
        for(int i = 0; i < 50; i++){
            orderList.add(new Order(random.nextInt(discSize+1)));
        }
       for(int i = 0; i < 10; i++){
    	   System.out.print(orderList.get(i).getBlockNumber() + " " + orderList.get(i).getDeadline());
    	   System.out.println();
        }
        
    }
    
}
