import java.util.List;
import java.util.ArrayList;
public class State {
    public int[][] mas;
    public int port;
    int maxSize;
    int n;
    List<Shipments> rezShipmentsList;

    public State(int n,int maxSize, int port)
    {
        this.mas = new int[n][n];
        this.maxSize=maxSize;
        this.port=port;
        this.n=n;
        rezShipmentsList = new ArrayList<Shipments>();
    }
    public int GetH(int[][] mas)
    {
        int h1=0;
        int h2=0;
        for (int i=0;i<n;i++)
        {
            int k1=0;
            int k2=0;
            for (int k=0;k<n;k++)
            {
                k1+=mas[i][k];
                k2+=mas[k][i];
            }
            h1+=Math.ceil(k1/Double.valueOf(maxSize));
            h2+=Math.ceil(k2/Double.valueOf(maxSize));
        }
       int h=Math.max(h1,h2);
        return h;
    }
    public int[][] trasformMas(Shipments shipments)
    {
        int[][] newMas = new int[n][n];
        //kopijuojam i nauja masyva
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                newMas[i][j]=mas[i][j];

        for(int i=0;i<n;i++)
        {
            newMas[this.port][i]-=shipments.cargo[i];//atimame is pradinio uosto
            if(i!=shipments.port) {
                newMas[shipments.port][i] += shipments.cargo[i];//pridatam tarpini krovini
            }
        }
        return newMas;
    }
    public  List<Shipments> GetPath()
    {
        List<Shipments> ShipmentsList = new ArrayList<Shipments>();
        int minH=n*n*maxSize;
        for(int i=0;i<n;i++) // i - ikuri plaukiam
        {
            if (i == port)
                continue;
            int maxForPort = Math.min(mas[port][i], maxSize);// maks kiek galime paimti
            for (int k = 0; k <= maxForPort; k++) {
                Shipments shipments = new Shipments(n, i);
                shipments.cargo[port] = 0;

                shipments.cargo[i] = k;
                for (int j = 0; j < n; j++)// j kiti uostai
                {
                    if (j == port || i == j)
                        continue;
                    int maxForPortNext = Math.min(mas[port][j], maxSize - k);// maks kiek galime paimti
                    for (int f = 0; f <= maxForPortNext; f++) {
                        shipments.cargo[j] = f;
                        int[][] b = trasformMas(shipments);
                        shipments.h = GetH(b);
                        if (shipments.h == 0)//viska pristateme
                        {
                            rezShipmentsList.add(shipments);
                            return rezShipmentsList;
                        }
                        ShipmentsList.add(shipments);
                        if (minH > shipments.h)
                            minH = shipments.h;
                    }
                }
            }
        }
        int tempMinH=9999;
        List<Shipments> tempShipmentsList  = new ArrayList<Shipments>();;
        Shipments tempShipments= null;
        for (Shipments s: ShipmentsList) {
            if(s.h==minH && s.cargo[0]+s.cargo[1]+s.cargo[2]>0) {
                State tempState=new State(n,maxSize,s.port);
                tempState.mas=this.trasformMas(s);
                List<Shipments> tempRez=tempState.GetPath();
                if(tempRez.get(tempRez.size()-1)!=null) {
                    if (tempRez.get(tempRez.size() - 1).h < tempMinH) {
                        tempShipmentsList = tempRez;
                        tempShipments = s;
                    }
                }
            }
        }
        rezShipmentsList=tempShipmentsList;
        rezShipmentsList.add(0,tempShipments);
        return tempShipmentsList;
    }
}