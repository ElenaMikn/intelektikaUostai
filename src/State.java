import java.util.Arrays;
public class State {
    public int[][] mas;
    public int port;
    int maxSize;
    int n;
    int h;
    public State(int n,int maxSize)
    {
        port=0;
        this.mas = new int[n][n];
        this.maxSize=maxSize;
        this.n=n;
    }
    public int GetH(int[][] mas)
    {
        //double f1 =Math.ceil();
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
            h1+=Math.ceil(k1/n);
            h2+=Math.ceil(k2/n);
        }
        h=Math.max(h1,h2);
        return h;
    }
    public int TryPort(int port, int[] vals)
    {
        int[][] b = Arrays.copyOf(mas, mas.length);
        b[port][port]-=vals[port];
        for(int i=0;i<n;i++)
        {
            if(i==port)
                continue;
            b[port][i]+=vals[port];
        }
        return GetH(b);
        //ArrayUtils.clone(a);
        //return 0;

    }
    public void GetPath()
    {
        for(int i=0;i<n;i++) // i - ikuri plaukiam
        {
            if(i==port)
                continue;
            int[] vals=new int[n];
            vals[port]=0;
            int maxForPort=Math.min(mas[port][i],maxSize);// maks kiek galime paimti
            for (int k=0;k<maxForPort;k++) {
                vals[i] =k;
                for(int j=0;j<n;j++)// j kiti uostai
                {
                    if(j==port || i==j)
                        continue;
                    int maxForPortNext=Math.min(mas[port][j],maxSize-k);// maks kiek galime paimti
                    for (int f=0;f<maxForPortNext;f++) {
                        vals[j] = f;
                        TryPort(i,vals);
                    }
                }
            }

        }
    }

}
