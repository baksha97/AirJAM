package plane;

import java.util.Arrays;
import java.util.Optional;

public enum CabinType {
    FIRST(1), ECONOMY(2);

    private final int value;

    CabinType(int value) {
        this.value = value;
    }

    public static Optional<CabinType> valueOf(int value){
        return Arrays.stream(values())
                .filter(cabinType -> cabinType.value == value)
                .findFirst();
    }
}
