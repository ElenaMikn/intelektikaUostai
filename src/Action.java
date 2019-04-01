import java.util.ArrayList;

public class Action {
    public int weight;
    public int weight2;
    public int weight3;
   // public int weight4;
    public String harbor;

 /*  public Action(int weight, int weight2, int weight3, int weight4, String harbor) {
        this.weight = weight;
        this.weight2 = weight2;
        this.weight3 = weight3;
        this.weight4 = weight4;

        this.harbor = harbor;
    }*/

    public Action(int weight, int weight2, int weight3,String harbor) {
        this.weight = weight;
        this.weight2 = weight2;
        this.weight3 = weight3;

        this.harbor = harbor;
    }


   public Action(Action toActionTemp) {
        this.weight = toActionTemp.weight;
        this.weight2 = toActionTemp.weight2;
        this.weight3 = toActionTemp.weight3;
        this.harbor =toActionTemp.harbor;
    }


    public static ArrayList<Action> cloneAction(Action toActionTemp){
        ArrayList<Action> forToAction=new ArrayList<>();
        forToAction.add(new Action(toActionTemp));

        return forToAction;
    }
}
