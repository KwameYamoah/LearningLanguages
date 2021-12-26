package Algorithms.Sorting;

public class SortStage {
    private boolean started,sorted;
    private boolean ascendingOrder;
    private int i,j;
    private int counter;
    private int targetA,targetB;

    public SortStage(int i, int j, int counter, boolean ascendingOrder) {
        this.i = i;
        this.j = j;
        this.counter = counter;
        this.ascendingOrder = ascendingOrder;
        sorted = false;
    }

    public SortStage() {
        this.i = 0;
        this.j = 0;
        this.counter = 0;
        this.ascendingOrder = true;
        sorted = false;
        started = false;
        targetA = -1;
        targetB = -1;
    }

    public void update(SortStage sortStage) {
        this.i = sortStage.getI();
        this.j = sortStage.getJ();
        this.ascendingOrder = sortStage.isAscendingOrder();
        this.counter = sortStage.getCounter();
        this.sorted = sortStage.isSorted();
    }

    public boolean isStarted() {
        return started;
    }
    public boolean isSorted() {
        return sorted;
    }
    public boolean isAscendingOrder() {
        return ascendingOrder;
    }
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    public int getCounter() {
        return counter;
    }
    public int getTargetA() {
        return targetA;
    }
    public int getTargetB() {
        return targetB;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
    public void setAscendingOrder(boolean ascendingOrder) {
        this.ascendingOrder = ascendingOrder;
    }
    public void setI(int i) {
        this.i = i;
    }
    public void setJ(int j) {
        this.j = j;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
    public void setCurrentTargets(int a, int b) {
        targetA = a;
        targetB = b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(started){
            sb.append("Sorted - ").append(sorted).append("/n");
            sb.append("Order of sorting - ");
            if(ascendingOrder) sb.append("Ascending").append("/n");
            else sb.append("Descending").append("/n");
            sb.append("Current targets - ").append("i : ").append(i).append(" j : ").append(j).append("/n");
        }
        else{
            sb.append("There is not current running sort");
        }
        return sb.toString();
    }

    public void reset() {
        this.i = 0;
        this.j = 0;
        this.counter = 0;
        this.ascendingOrder = true;
        sorted = false;
        started = false;
        targetA = -1;
        targetB = -1;
    }
}
