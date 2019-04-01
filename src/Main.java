import java.lang.reflect.Array;
import java.util.*;

public class Main {
    //public static ArrayList<ArrayList> tempListOfShipments;
    static ArrayList<ArrayList<Shipment>> tempListOfShipments=new ArrayList<ArrayList<Shipment>>();
    static ArrayList<ArrayList> ListOfShipments=new ArrayList<ArrayList>();
    static ArrayList<Action> toAction= new ArrayList<Action>();
    static ArrayList<Action> toActionTemp= new ArrayList<Action>();
    static ArrayList<ArrayList> forTempListOfShipments=new ArrayList<ArrayList>();
    static ArrayList<objectTree> result=new ArrayList<>();
    static ArrayList<Action> finalActionList= new ArrayList<>();
    static ArrayList<Action> finalActionListReverse= new ArrayList<>();

    static objectTree actualObject;
    static double f;
    static double f1;
    static double f2;
    static double f3;
    static double f4;
    static double f34;
    static int count=0;
    static int levelCount=0;



    static int level=0;
    static int [] tempAction;
    static int counterZero;
    static int saveSkipped;
    static double saveF=-1;
    static double tempSaveF;
    //static int howMuchStates;
    static int notZeros;
    static int isTransferable;
    static int isTransferableMod;
    static boolean checkRepeated;



    public static void Skaiciavimas(Ship ship, Harbor harbor,Shipments shipments, Shipments shipmentsClone, ArrayList<ArrayList> forTempListOfShipments, int maxWeight){
      //išsaugome į kintamajį porto kuriame yra dabar laivas eilutės pirmojo elemento reikšmę tolimesniam naudojimui
        saveSkipped=harbor.getHarborSize()*harbor.getHarborsNames().indexOf(shipments.getInitialHarbor());

        //for ciklas navigacijai tarp matricos eilučiu(elementų)

        for (int harCountMultiplier = 0; harCountMultiplier <= Math.pow(Double.valueOf(harbor.getHarborSize()) - 1, 2) * 2; harCountMultiplier = harCountMultiplier + harbor.getHarborSize()) {

           // System.out.println("Prasideda pirmas FOR ciklas " + harCountMultiplier);

            tempAction = new int[harbor.getHarborSize()];
            //  int newAray[][] = new int[3][3];

            //praleidžiam eilutę jeigu ji sutampa su siuntejo portu
            if (shipments.getListOfShipments().get(harCountMultiplier).getSender() == shipments.getInitialHarbor()) {
                saveSkipped = harbor.getHarborSize() * harbor.getHarborsNames().indexOf(shipments.getInitialHarbor());
             //   System.out.println("Praleidžiamas portas kuriame laivas: " + shipments.getInitialHarbor());

                continue;
            }
            //for ciklas galimų veiksmų generavimui
            for (int harCountCount = 0; harCountCount <= harbor.getHarborSize(); harCountCount++) {
               // System.out.print("harCountCount: " + harCountCount);

                //for ciklas prieigai prie eilutės elemento numerio
                for (int harCount = 0; harCount < harbor.getHarborSize(); harCount++) {

                   // System.out.println("  harCount: " + harCount);

                    if (harCount == harCountCount || counterZero == harbor.getHarborSize()) {
                        tempAction[harCount] = tempAction[harCount] * 0;
                       // System.out.println("Čia veiksmas lygus nuliui: " + tempAction[harCount] );
                    } else {
                        tempAction[harCount] = shipments.getListOfShipments().get(harCount + saveSkipped).getWeight();

                       // System.out.println("Generuojamas veiksmas: " + tempAction[harCount]);
                        //System.out.println("shipments.getListOfShipments().get(harCount + saveSkipped): " + harCount +" + "+ saveSkipped);


                    }
                }
                //išsaugome veiksmą
                int showTempAction = tempAction[0] + tempAction[1] + tempAction[2];
               // System.out.println("Sugeneruotas veiksmas: " + tempAction[0] + tempAction[1] + tempAction[2] + harbor.getHarborsNames().get(harCountMultiplier / harbor.getHarborSize()));

                //jeigu veiksmas neviršyja maksimalios laivo pervežimo masės
                if (showTempAction <= maxWeight) {
                    //perskaičiuojame būseną pagal iki to nustatytą veiksmą
                    for (int x = 0; x < harbor.getHarborSize(); x++) {
                        //apskaičiuojame kiek reikia pridėti ciklo elementui (naudojama harCountMultiplier reikšmė prieigai prie eilutės)
                        int sum = shipments.getListOfShipments().get(harCountMultiplier + x).getWeight() + tempAction[x];
                        //apskaičiuojame kiek reikia atimti iš ciklo elemento

                        int substract = shipments.getListOfShipments().get(saveSkipped + x).getWeight() - tempAction[x];
                                             //eilutė porto siuntėjo apskaičiavimo
                        shipmentsClone.getListOfShipments().get(saveSkipped + x).setWeight(substract);
                          //priskiriam matricos elementams naujai apskaičiuotas reikšmes po  atlikto veiksmo tempAction[]

                        shipmentsClone.getListOfShipments().get(harCountMultiplier + x).setWeight(sum);
                        //priskiriam matricos įstrižainėi nulius (šie elementai visada nuliniai)
                        shipmentsClone.getListOfShipments().get(harCountMultiplier + harCountMultiplier / harbor.getHarborSize()).setWeight(0);
                    }
                    //apskačiuojame matricos F reikšmę
                    //System.out.println("F reikšmė  objekto" + Shipments.getF(shipments.getListOfShipments(), ship));

                  //  System.out.println("F reikšmė klonuoto objekto " + Shipments.getF(shipmentsClone.getListOfShipments(), ship));

                    //išsaugome f reikšme tolimesniam palyginimui
                    tempSaveF = Shipments.getF(shipmentsClone.getListOfShipments(), ship);
                //    System.out.println("Mažiausias F " + tempSaveF);

                    //lyginame būsenas tarpusavyje, išsaugome didžiausias į masyvų masyvą
                    if (tempSaveF == saveF) {
                        for(ArrayList<Shipment> comp:tempListOfShipments) {
                            checkRepeated = checkRepeated || comp.equals(shipmentsClone.getListOfShipments());
                          //  System.out.println("T/F kai lygu: "+comp.equals(shipmentsClone.getListOfShipments()));

                        }
                        //System.out.println("checkRepeated kai lygu: " +checkRepeated);
                        if(!checkRepeated){
                            forTempListOfShipments.add(shipmentsClone.getListOfShipments());
                            tempListOfShipments.add(Shipment.cloneList(shipmentsClone.getListOfShipments()));
                           toActionTemp.add(new Action(tempAction[0], tempAction[1], tempAction[2], harbor.getHarborsNames().get(harCountMultiplier / harbor.getHarborSize())));
                            objectTree forObjectTree=new objectTree(Shipment.cloneList(shipments.getListOfShipments()),Shipment.cloneList(shipmentsClone.getListOfShipments()),new Action(tempAction[0], tempAction[1], tempAction[2], harbor.getHarborsNames().get(harCountMultiplier / harbor.getHarborSize())),tempSaveF,levelCount);
                            result.add(forObjectTree);
                        }
                        checkRepeated=false;}
                    if (tempSaveF < saveF || saveF == -1) {
                        for (ArrayList<Shipment> comp : tempListOfShipments) {
                            checkRepeated = checkRepeated || comp.equals(shipmentsClone.getListOfShipments());
                          //  System.out.println("T/F kai mažiau: " + comp.equals(shipmentsClone.getListOfShipments()));
                        }
                    //    System.out.println("checkRepeated kai mažiau: " + checkRepeated);

                        if (!checkRepeated) {
                                                       saveF = tempSaveF;
                            forTempListOfShipments.clear();
                            toActionTemp.clear();
                            toActionTemp.add(new Action(tempAction[0], tempAction[1], tempAction[2], harbor.getHarborsNames().get(harCountMultiplier / harbor.getHarborSize())));
                            forTempListOfShipments.add(shipmentsClone.getListOfShipments());
                            objectTree forObjectTree=new objectTree(Shipment.cloneList(shipments.getListOfShipments()),Shipment.cloneList(shipmentsClone.getListOfShipments()),new Action(tempAction[0], tempAction[1], tempAction[2], harbor.getHarborsNames().get(harCountMultiplier / harbor.getHarborSize())),tempSaveF,levelCount);
                            result.add(forObjectTree);
                            int size=result.size();
                            if(size>1){
                                if(result.get(size-1).res<result.get(size-2).res&&result.get(size-1).level==result.get(size-2).level)result.remove(size-2);
                            }

                            tempListOfShipments.clear();
                   tempListOfShipments.add(Shipment.cloneList(shipmentsClone.getListOfShipments()));
                                          }
                        checkRepeated=false;
                     } }
              counterZero++;
            }
            counterZero = 0;
            isTransferable=0;
            notZeros=0;

        }

    }


    public static void main(String[] args){

        Harbor harbor=new Harbor();
        ArrayList<String> toHarborsNames=new ArrayList<String>();
        toHarborsNames.add("KLP");
        toHarborsNames.add("NY");
        toHarborsNames.add("SID");
        harbor.setHarborsNames(toHarborsNames);

        ArrayList<String> fromHarborsNames=new ArrayList<String>();
        fromHarborsNames=harbor.getHarborsNames();

        System.out.println(harbor.getHarborsNames());
        System.out.println(fromHarborsNames.get(1));
        System.out.println(fromHarborsNames.get(0));
        System.out.println(fromHarborsNames.get(2));

       /*
       Laikinai užkomentuotas naudotojo įvedimas
       Scanner readShipMaxWeight = new Scanner(System.in);
        System.out.println("Įveskite laivo keliamąją galią: ");
        int maxWeight=readShipMaxWeight.nextInt();
        readShipMaxWeight.close();*/

        int maxWeight=5;

        Ship ship=new Ship(maxWeight, fromHarborsNames.get(0));
        System.out.println("Laivo keliamoji galia: " +ship.getMaxWeight());
        System.out.println("Laivo uostas: " +ship.getHarborFrom());

        ArrayList<ArrayList> toMainShipments= new ArrayList<ArrayList>();

        ArrayList<Shipment> toShipments= new ArrayList<Shipment>();
        ArrayList<Shipment> toShipmentsClone= new ArrayList<Shipment>();



        //visos siuntos (iš, į , svoris)
        toShipments.add(new Shipment(fromHarborsNames.get(0), fromHarborsNames.get(0), 0));
        toShipments.add(new Shipment(fromHarborsNames.get(0), fromHarborsNames.get(1), 3));
        toShipments.add(new Shipment(fromHarborsNames.get(0), fromHarborsNames.get(2), 1));
        toShipments.add(new Shipment(fromHarborsNames.get(1), fromHarborsNames.get(0), 2));
        toShipments.add(new Shipment(fromHarborsNames.get(1), fromHarborsNames.get(1), 0));
        toShipments.add(new Shipment(fromHarborsNames.get(1), fromHarborsNames.get(2), 4));
        toShipments.add(new Shipment(fromHarborsNames.get(2), fromHarborsNames.get(0), 3));
        toShipments.add(new Shipment(fromHarborsNames.get(2), fromHarborsNames.get(1), 0));
        toShipments.add(new Shipment(fromHarborsNames.get(2), fromHarborsNames.get(2), 0));

        toShipmentsClone.add(new Shipment(fromHarborsNames.get(0), fromHarborsNames.get(0), 0));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(0), fromHarborsNames.get(1), 3));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(0), fromHarborsNames.get(2), 1));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(1), fromHarborsNames.get(0), 2));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(1), fromHarborsNames.get(1), 0));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(1), fromHarborsNames.get(2), 4));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(2), fromHarborsNames.get(0), 3));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(2), fromHarborsNames.get(1), 0));
        toShipmentsClone.add(new Shipment(fromHarborsNames.get(2), fromHarborsNames.get(2), 0));

        //masyvas masyvų laikinam saugojimui
        ArrayList<ArrayList> forTempListOfShipments = new ArrayList<ArrayList>();

        Shipments shipments=new Shipments(toShipments);

        Shipments shipmentsClone=new Shipments(toShipmentsClone);
        //Pradinio porto pavadinimas portų objekte
        shipments.setInitialHarbor(harbor.getHarborsNames().get(0));


        Skaiciavimas(ship,harbor,shipments,shipmentsClone,forTempListOfShipments,maxWeight);

        while(shipments.getListOfShipments().get(0).getWeight()!=0 || shipments.getListOfShipments().get(1).getWeight()!=0||shipments.getListOfShipments().get(2).getWeight()!=0
            ||shipments.getListOfShipments().get(3).getWeight()!=0||shipments.getListOfShipments().get(4).getWeight()!=0||shipments.getListOfShipments().get(5).getWeight()!=0
            ||shipments.getListOfShipments().get(5).getWeight()!=0||shipments.getListOfShipments().get(6).getWeight()!=0||shipments.getListOfShipments().get(7).getWeight()!=0)
        {
            ListOfShipments.clear();
            toAction.clear();
            saveF=-1;
            levelCount++;
            for(int forArrAccess=0;forArrAccess<tempListOfShipments.size();forArrAccess++) {
                ListOfShipments.add(Shipment.cloneArrayList(tempListOfShipments.get(forArrAccess)));
                toAction.add(new Action(toActionTemp.get(forArrAccess)));
              //  System.out.println("Teisingas veiksmas: " +toActionTemp.get(forArrAccess).weight+toActionTemp.get(forArrAccess).weight2+toActionTemp.get(forArrAccess).weight3+toActionTemp.get(forArrAccess).harbor);

            }
            int limit=ListOfShipments.size();
            for(int a=0;a<limit;a++) {
              //  System.out.println("Objektas kopijuojamas kaip pagrindinis nr.+++++++++++++++++++++++++++++++++: " +a);
                shipments.setListOfShipments(ListOfShipments.get(a));
                shipmentsClone.setListOfShipments(Shipment.cloneArrayList(ListOfShipments.get(a)));
               shipments.setInitialHarbor(toAction.get(a).harbor);

                Skaiciavimas(ship,harbor,shipments,shipmentsClone,forTempListOfShipments,maxWeight);

            }

        }
        actualObject=result.get(result.size()-1);
        finalActionList.add(actualObject.act);
        for(int h=result.size()-2;h>=0;h--) {
            if (result.get(h).child.equals(actualObject.parent)) {
                actualObject = result.get(h);
                finalActionList.add(actualObject.act);

            }

        }
        Collections.reverse(finalActionList);
        System.out.println("Veiskmų seka:  ");

        for(Action finAct: finalActionList)
            System.out.println(" " +finAct.weight + finAct.weight2 + finAct.weight3 + finAct.harbor);

        System.out.print("Programos pabaiga");

    }


}
//
