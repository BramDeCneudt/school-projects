package bdc.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class CitiesNames {

    HashSet<String> names;
    Iterator<String> it;

    public CitiesNames() {
        names = new HashSet<>();
        names.add("Blanden");
        names.add("Leuven");
        names.add("Haasrode");
        names.add("Brugge");
        it = this.names.iterator();
    }

    public List<String> getNames() {
        return new ArrayList<>(this.names);
    }

    public String getNextCity() {
        if (!it.hasNext()) {
            it = this.names.iterator();
        }
        return it.next();
    }

}
