import java.util.List;

public abstract class AccessAlgorithm {
    protected List<Order> queue = null;
    protected int discSize = 0;
    protected int currentPosition;
    protected int displacement = 0;

    void clearQueue() {
        for (Order o:queue) {
            o.setServed(false);
        }
    }

    boolean ended() {
        for (Order order: queue) {
            if (!order.isServed()){
                return false;
            }
        }
        return true;
    }

    public abstract double getDisplacement();
}