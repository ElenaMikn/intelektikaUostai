import java.util.ArrayList;

public class Shipment {
    private String sender;
    private String recipient;
    private int weight;

    /*public Shipment(Shipment thisShipment) {
        this.sender=thisShipment.getSender();
    }*/

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Shipment)) {
            return false;
        }

        Shipment other = (Shipment) obj;

        return getSender() == other.getSender() && getRecipient() == other.getRecipient() && getWeight() == other.getWeight();
    }
//atrodo kad jie dubliuojasi
    public static ArrayList<Shipment> cloneList(ArrayList<Shipment> fromShipmentsClone){
        ArrayList<Shipment> clonesListShipm=new ArrayList<Shipment>(fromShipmentsClone.size());
        for (Shipment shipmentsFor: fromShipmentsClone){
            clonesListShipm.add(new Shipment(shipmentsFor));
        }
        return clonesListShipm;
    }
//atrodo kad jie dubliuojasi

    public static ArrayList<Shipment> cloneArrayList(ArrayList<Shipment> tempListOfShipments){
       ArrayList<Shipment> clonesListArray=new ArrayList<Shipment>(tempListOfShipments.size());
        for (Shipment shipmentsForArrayClone: tempListOfShipments){
            clonesListArray.add(new Shipment (shipmentsForArrayClone));
        }
        return clonesListArray;
    }

  /*  public static Shipment cloneArrayListToShipmentsClone(Shipment tempListOfShipments){
        ArrayList<Shipment> clonesListArray=new ArrayList<Shipment>(tempListOfShipments.size());
        for (Shipment shipmentsForArrayClone: tempListOfShipments){
            clonesListArray.add(shipmentsForArrayClone);
        }
        return clonesListArray;
    }
*/
    public Shipment(Shipment shipmentsFor) {
        this.sender = shipmentsFor.sender;
        this.recipient=shipmentsFor.recipient;
        this.weight=shipmentsFor.weight;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public int getWeight() {
        return weight;
    }

    public Shipment(String sender, String recipient, int weight) {
        this.sender = sender;
        this.recipient = recipient;
        this.weight = weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

}
