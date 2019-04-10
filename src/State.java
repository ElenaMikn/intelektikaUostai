import java.util.List;
import java.util.ArrayList;
public class State {
    public int[][] mas;
    public int port;
    int maxSize;
    int n;
    int h;
    List<Shipments> rezShipmentsList;

    public State(int n,int maxSize, int port, int h)
    {
        this.mas = new int[n][n];
        this.maxSize=maxSize;
        this.port=port;
        this.n=n;
        this.h=h;
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
        /*if(this.h==1)
        {
            System.out.println(this.port);
        }*/
        List<Shipments> ShipmentsList = new ArrayList<Shipments>();
        int minH=2147483647;
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
                        if (minH > shipments.h) {
                            minH = shipments.h;
                        }
                    }
                }
            }
        }

        int tempMinH=2147483647;
        List<Shipments> tempShipmentsList  = new ArrayList<Shipments>();;
        Shipments tempShipments= null;
        for (Shipments s: ShipmentsList) {
            if(s.h==minH && s.cargo[0]+s.cargo[1]+s.cargo[2]>0 ) {
                State tempState=new State(n,maxSize,s.port,minH);
                tempState.mas=this.trasformMas(s);
                List<Shipments> tempRez=tempState.GetPath();
                if(tempRez.get(tempRez.size()-1)!=null) {
                    if (tempRez.get(tempRez.size() - 1).h <= tempMinH) {
                        tempShipmentsList = tempRez;
                        tempShipments = s;
                        if(s.h==0)
                        {
                            break;
                        }
                    }
                }
                s=null;
            }
        }

        if(tempShipmentsList.size()==0) //skirta kai reikia plaukti tusciam
        {
            for(int i=0;i<n;i++)
            {
                if (i == port)
                    continue;
                for(int k=0;k<n;k++)
                {
                    if (this.mas[i][k]>0) { // randam uosta kuriame dar yra kroviniu
                        Shipments s=new Shipments(n,i);
                        for (int f = 0; f < n; f++) {
                            s.cargo[f] = 0;
                        }
                        State tempState=new State(n,maxSize,s.port,minH);
                        tempState.mas=this.trasformMas(s);
                        List<Shipments> tempRez=tempState.GetPath();
                        if(tempRez.get(tempRez.size()-1)!=null) {
                            if (tempRez.get(tempRez.size() - 1).h <= tempMinH) {
                                tempShipmentsList = tempRez;
                                tempShipments = s;
                                if(s.h==0)
                                {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        rezShipmentsList=tempShipmentsList;
        rezShipmentsList.add(0,tempShipments);
        return tempShipmentsList;
    }
}