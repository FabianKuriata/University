import static java.lang.Math.abs;

import java.util.List;

public class EDF extends AccessAlgorithm{
    private int move = 1;

    public EDF(List<Order> queue, int discSize){
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
        Order earliestOrder = findEarliest();
        if(currentPosition < earliestOrder.getBlockNumber()){
            move = 1;
        }else{
            move = -1;
        }
        currentPosition += move;
        displacement++;
    }

    private Order findEarliest() {
        Order earliest = null;
        int earliestPriority = 20;
        for (Order o:queue) {
            if (!o.isServed() && o.getDeadline() < earliestPriority){
                earliest = o;
                earliestPriority = earliest.getDeadline();
            }
        }
        if (earliest == null){
            earliest = findNearest();
        }
        return earliest;
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
