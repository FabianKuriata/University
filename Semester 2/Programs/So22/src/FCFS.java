import java.util.List;

public class FCFS extends AccessAlgorithm {

    public FCFS(List<Order> queue, int discSize){
        this.queue = queue;
        this.discSize = discSize;
        currentPosition = queue.get(0).getBlockNumber();
    }

    public double getDisplacement(){
        for (Order o: queue) {
            int target = o.getBlockNumber();
            displacement += Math.abs(currentPosition - target);
            currentPosition = target;
            o.setServed(true);
        }

        clearQueue();

        return displacement;
    }
}
