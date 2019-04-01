import java.util.ArrayList;

public class Shipments {

    public ArrayList<Shipment> listOfShipments;
    public String initialHarbor;
    public ArrayList<Action> listOfActions;

  /*  public ArrayList<ArrayList> getTempListOfShipments() {
        return tempListOfShipments;
    }

    public void setTempListOfShipments(ArrayList<ArrayList> tempListOfShipments) {
        this.tempListOfShipments = tempListOfShipments;
    }
*/
    public Shipments(Shipments another) {
        this.listOfShipments = another.listOfShipments;
    }

    public Shipments(ArrayList<Shipment> listOfShipments) {
        this.listOfShipments = listOfShipments;
    }

    public String getInitialHarbor() {
        return initialHarbor;
    }

    public void setInitialHarbor(String initialHarbor) {
        this.initialHarbor = initialHarbor;
    }


    public void setListOfShipments(ArrayList<Shipment> listOfShipments) {
        this.listOfShipments = listOfShipments;
    }

    public void clearListOfShipments(ArrayList<Shipment> listOfShipments) {
        this.listOfShipments = listOfShipments;
    }

    public ArrayList<Shipment> getListOfShipments() {
        return listOfShipments;
    }

    public static double getF (ArrayList<Shipment> list, Ship intForWeight){
        double f1 =Math.ceil((Double.valueOf(list.get(0).getWeight())+Double.valueOf(list.get(1).getWeight())+Double.valueOf(list.get(2).getWeight()))/Double.valueOf(intForWeight.getMaxWeight()))+
        Math.ceil((Double.valueOf(list.get(3).getWeight())+Double.valueOf(list.get(4).getWeight())+Double.valueOf(list.get(5).getWeight()))/Double.valueOf(intForWeight.getMaxWeight()))+
        Math.ceil((Double.valueOf(list.get(6).getWeight())+Double.valueOf(list.get(7).getWeight())+Double.valueOf(list.get(8).getWeight()))/Double.valueOf(intForWeight.getMaxWeight()));

        double f2=Math.ceil((Double.valueOf(list.get(0).getWeight())+Double.valueOf(list.get(3).getWeight())+Double.valueOf(list.get(6).getWeight()))/Double.valueOf(intForWeight.getMaxWeight()))+
            Math.ceil((Double.valueOf(list.get(1).getWeight())+Double.valueOf(list.get(4).getWeight())+Double.valueOf(list.get(7).getWeight()))/Double.valueOf(intForWeight.getMaxWeight()))+
            Math.ceil((Double.valueOf(list.get(2).getWeight())+Double.valueOf(list.get(5).getWeight())+Double.valueOf(list.get(8).getWeight()))/Double.valueOf(intForWeight.getMaxWeight()));

        return Math.max(f1,f2);

    }
}
