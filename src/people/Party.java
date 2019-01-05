package people;

import plane.CabinType;

import java.util.ArrayList;
import java.util.HashSet;

public class Party {
    private final int MAX_SIZE = 3;
    private ArrayList<Person> members;
    private CabinType requestedCabin;
    private HashSet<SeatType> requestedSeatTypes;

    public Party(CabinType requestedCabin, Person... people) {
        if (people.length > MAX_SIZE) throw new IllegalStateException("Parties cannot be larger than three");

        setMembers(new ArrayList<>());
        setRequestedSeatTypes(new HashSet<>());
        setRequestedCabin(requestedCabin);

        for (Person p : people) {
            members.add(p);
            if (requestedSeatTypes.contains(p.getSeatPreference()))
                throw new IllegalArgumentException("Party must have unique seat preferences to be seated near one another");
            if (p.getSeatPreference() == SeatType.CENTER && requestedCabin == CabinType.FIRST)
                throw new IllegalArgumentException("There exists no center seat in First class.");
            requestedSeatTypes.add(p.getSeatPreference());
        }
    }

    public String toString() {
        return members.toString();
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    private void setMembers(ArrayList<Person> members) {
        this.members = members;
    }

    public CabinType getRequestedCabin() {
        return requestedCabin;
    }

    private void setRequestedCabin(CabinType requestedCabin) {
        this.requestedCabin = requestedCabin;
    }

    public HashSet<SeatType> getRequestedSeatTypes() {
        return requestedSeatTypes;
    }

    private void setRequestedSeatTypes(HashSet<SeatType> requestedSeatTypes) {
        this.requestedSeatTypes = requestedSeatTypes;
    }

}
