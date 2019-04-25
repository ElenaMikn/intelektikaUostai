import java.util.ArrayList;

public class Shipments {
    public int[] cargo; // krovinis
    public int port; // i kuri plaukia
    public int h; //
    public int n; // uostu kiekis
    public Shipments(int n, int port)
    {
        this.n=n;
        this.port=port;
        cargo=new int[n];
    }
}
