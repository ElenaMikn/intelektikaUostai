import java.util.ArrayList;

public class objectTree {

    public objectTree(ArrayList<Shipment> parent, ArrayList<Shipment> child, Action act, double res, int level) {
        this.parent = parent;
        this.child = child;
        this.act = act;
        this.res = res;
        this.level = level;

    }

    public ArrayList<Shipment> parent;
    public ArrayList<Shipment> child;
    public Action act;
    public double res;
    public int level;

}
