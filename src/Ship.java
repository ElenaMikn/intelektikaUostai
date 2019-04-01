public class Ship {

    private int maxWeight;
    private int weight;

    private String harborFrom;
    private String harborTo;

    public Ship(int maxWeight, String harborFrom) {
        this.maxWeight = maxWeight;
        this.weight = 0;
        this.harborFrom = harborFrom;
        this.harborTo = null;
    }

    public Ship(int maxWeight, int weight, String harborFrom, String harborTo) {
        this.maxWeight = maxWeight;
        this.weight = weight;
        this.harborFrom = harborFrom;
        this.harborTo = harborTo;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public String getHarborFrom() {
        return harborFrom;
    }
}
