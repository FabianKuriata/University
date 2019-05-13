
import java.util.List;

public class CSCAN extends AccessAlgorithm {
    int move = 1;

    public CSCAN(List<Order> queue, int discSize){
        this.queue = queue;
        this.discSize = discSize;
        currentPosition = 0;
    }

    @Override
    public double getDisplacement() {
        while(!ended()){
            moveHead();
            for (Order order:queue) {
                if (currentPosition == order.getBlockNumber()){
                    order.setServed(true);
                }
            }
        }

        clearQueue();

        return displacement;
    }

    private void moveHead() {
        if(currentPosition == discSize){
            displacement += discSize;
            currentPosition = 0;
        }else{
            currentPosition++;
            displacement++;
        }
    }
}