import java.lang.reflect.Array;
import java.util.*;

public class Main {


    public static void main(String[] args) {
    State s=new State(3,5,0);
        s.mas[0][0]=0;
        s.mas[0][1]=3;
        s.mas[0][2]=1;
        s.mas[1][0]=2;
        s.mas[1][1]=0;
        s.mas[1][2]=4;
        s.mas[2][0]=3;
        s.mas[2][1]=0;
        s.mas[2][2]=0;
        //s.GetH();
        List<Shipments> tempShipmentsList  = s.GetPath();
        for (Shipments st: tempShipmentsList) {
            System.out.println(st.port);

        }
    }


}
//
