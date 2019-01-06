package people;

public enum SeatType {
    WINDOW(0), CENTER(1), AISLE(2);

    private final int value;

    SeatType(int value) {
        this.value = value;
    }

    public static SeatType fromInt(int i){
        if(i == 0) return WINDOW;
        else if(i == 1) return CENTER;
        else if(i == 2) return AISLE;
        else return null;
    }
}
