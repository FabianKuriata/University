import java.util.List;

public class SCAN extends AccessAlgorithm {
    int move = 1;

    public SCAN(List<Order> queue, int discSize){
        this.queue = queue;
        this.discSize = discSize;
        currentPosition = queue.get(0).getBlockNumber();
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
        if(currentPosition == 0){
            move = 1;
        }else if(currentPosition == discSize){
            move = -1;
        }
        currentPosition += move;
        displacement++;
    }
}
