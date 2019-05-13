import static java.lang.Math.abs;

import java.util.List;

public class SSTF extends AccessAlgorithm {

    public SSTF(List<Order> queue, int discSize){
        this.queue = queue;
        this.discSize = discSize;
        currentPosition = queue.get(0).getBlockNumber();
    }

    @Override
    public double getDisplacement() {

        while (!ended()){
            Order nearestOrder = findNearest();
            int target = nearestOrder.getBlockNumber();
            displacement += Math.abs(currentPosition - target);
            currentPosition = target;
            nearestOrder.setServed(true);
        }

        clearQueue();

        return displacement;
    }

    private Order findNearest() {
        int distance = discSize;
        Order nearest = null;
        for (Order order:queue) {
            if (abs(order.getBlockNumber() - currentPosition) < distance && !order.isServed()) {
                distance = abs(order.getBlockNumber() - currentPosition);
                nearest = order;
            }
        }
        return nearest;
    }
}
