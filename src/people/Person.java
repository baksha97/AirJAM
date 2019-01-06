package people;

import plane.SeatType;

public class Person {

    private final SeatType seatPreference;
    private String name;

    public Person(String n, SeatType seatPreference) {
        setName(n);
        this.seatPreference = seatPreference;
    }

    public SeatType getSeatPreference() {
        return seatPreference;
    }

    @SuppressWarnings("WeakerAccess")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        String s = getName();
        return "(" + s + ")";
    }

}
