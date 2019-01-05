package plane;

import people.Party;

import java.util.HashMap;

public class Plane {

    private final HashMap<CabinType, Cabin> cabins;

    public Plane(int size) {
        cabins = new HashMap<>();
        cabins.put(CabinType.FIRST, new Cabin(CabinType.FIRST, size / 2));
        cabins.put(CabinType.ECONOMY, new Cabin(CabinType.ECONOMY, size));
    }

    public boolean addParty(Party p) {
        if (cabins.get(p.getRequestedCabin()).addParty(p)) {
            System.out.println("Party: " + p);
            System.out.println("... successfully added");
            return true;
            //TODO: add boolean check and prints in the main
        } else {
            System.out.println("Unable to fit your party in the cabin!");
            return false;
        }
    }

    @Override
    public String toString() {
        return cabins.get(CabinType.FIRST).toString() + '\n'
                + cabins.get(CabinType.ECONOMY).toString();
    }
}
