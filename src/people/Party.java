package people;

import plane.CabinType;

import java.util.ArrayList;
import java.util.HashSet;

public class Party {
    private final int MAX_SIZE = 3;
    private ArrayList<Person> members;
    private CabinType requestedCabin;
    private HashSet<SeatType> requestedSeatTypes;
    private HashSet<SeatType> openSeatTypes;

    public Party(CabinType requestedCabin, Person... people) {
        setMembers(new ArrayList<>());
        setRequestedSeatTypes(new HashSet<>());
        setRequestedCabin(requestedCabin);
        addPerson(people);
    }

    public void addPerson(Person... people){
        for (Person p : people) {
            members.add(p);
            if (requestedSeatTypes.contains(p.getSeatPreference()))
                throw new IllegalArgumentException("Party must have unique seat preferences to be seated near one another");
            if (p.getSeatPreference() == SeatType.CENTER && requestedCabin == CabinType.FIRST)
                throw new IllegalArgumentException("There exists no center seat in First class.");
            requestedSeatTypes.add(p.getSeatPreference());
        }
        if (members.size() > MAX_SIZE) throw new IllegalStateException("Parties cannot be larger than three");
    }

    public boolean isFull(){
        return members.size() > MAX_SIZE;
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
        setOpenSeatTypes(new HashSet<>());
        switch(requestedCabin){
            case FIRST:
                openSeatTypes.add(SeatType.WINDOW);
                openSeatTypes.add(SeatType.AISLE);
                break;
            case ECONOMY:
                openSeatTypes.add(SeatType.WINDOW);
                openSeatTypes.add(SeatType.CENTER);
                openSeatTypes.add(SeatType.AISLE);
                break;
        }
    }

    public HashSet<SeatType> getRequestedSeatTypes() {
        return requestedSeatTypes;
    }

    private void setRequestedSeatTypes(HashSet<SeatType> requestedSeatTypes) {
        this.requestedSeatTypes = requestedSeatTypes;
    }

    public HashSet<SeatType> getOpenSeatTypes() {
        return openSeatTypes;
    }

    private void setOpenSeatTypes(HashSet<SeatType> openSeatTypes) {
        this.openSeatTypes = openSeatTypes;
    }
}
