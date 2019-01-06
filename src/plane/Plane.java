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

    public boolean canAddParty(Party p) {
        Cabin requested = cabins.get(p.getRequestedCabin());
        return requested.canAddParty(p);
    }

    public void addParty(Party p) {
        if (!canAddParty(p))
            throw new IllegalArgumentException("Cannot add party to cabin");
        Cabin requested = cabins.get(p.getRequestedCabin());
        requested.addParty(p);
    }

    @Override
    public String toString() {
        return cabins.get(CabinType.FIRST).toString() + '\n'
                + cabins.get(CabinType.ECONOMY).toString();
    }
}
