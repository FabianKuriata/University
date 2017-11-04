import java.util.Random;

public class Order {
    private int deadline;
    private int blockNumber;
    private boolean served;

    public Order(int blockNumber) {
        Random random = new Random();
        this.blockNumber = blockNumber;
        if (random.nextInt(8)%5 == 0){
            this.deadline = random.nextInt(19) + 1;
        }else {
            this.deadline = 20; // no deadline
        }
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    public int getDeadline() {
        return deadline;
    }
}

