import java.util.ArrayList;

public class Shipments {
    public int[] vals;
    public int port;
    public int h;
    public int n;
    public Shipments(int n, int port)
    {
        this.n=n;
        this.port=port;
        vals=new int[n];
    }
}
