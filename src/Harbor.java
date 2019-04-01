import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Harbor{

    private ArrayList<String> harborsNames;

    public void setHarborsNames(ArrayList<String> harborsNames) {
        this.harborsNames = harborsNames;
    }

    public ArrayList<String> getHarborsNames() {
        return harborsNames;
    }

    public int getHarborSize() {
        return harborsNames.size();
    }

}
