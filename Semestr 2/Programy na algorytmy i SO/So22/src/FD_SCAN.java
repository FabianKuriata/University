import static java.lang.Math.abs;

import java.util.List;

public class FD_SCAN extends AccessAlgorithm{ // do najbli¿szego z priorytetem
    int move = 1;

    public FD_SCAN(List<Order> queue, int discSize){
        this.queue = queue;
        this.discSize = discSize;
        currentPosition = queue.get(0).getBlockNumber();
    }

    @Override
    public double getDisplacement() {
        while (!ended()){
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
        Order earliestOrder = findNearestPriority();
        if(currentPosition < earliestOrder.getBlockNumber()){
            move = 1;
        }else{
            move = -1;
        }
        currentPosition += move;
        displacement++;
    }

    private Order findNearestPriority() {
        Order nearest = null;
        int shortestDistance = discSize;
        for (Order order: queue) {
            if (!order.isServed() && order.getDeadline() < 20) {
                int distance = Math.abs(order.getBlockNumber() - currentPosition);
                if (distance < shortestDistance) {
                    nearest = order;
                    shortestDistance = distance;
                }
            }
        }
        if (nearest == null){
            nearest = findNearest();
        }
        return nearest;
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