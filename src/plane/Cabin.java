package plane;

import people.Party;
import people.Person;
import people.SeatType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Cabin {
    private final CabinType type;
    private final ArrayList<Party> parties;
    private final CabinRow[] cabinRows;

    Cabin(CabinType type, int size) {
        this.type = type;
        this.parties = new ArrayList<>();
        this.cabinRows = CabinRow.forTypeAndSize(type, size);
    }

    public boolean addParty(Party party) {
        for (CabinRow row : cabinRows) {
            if (row.canFitParty(party)) {
                row.addParty(party);
                parties.add(party);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.toString()).append('\n');
        for (CabinRow cr : cabinRows) {
            sb.append('\n');
            sb.append(cr.toString());
            sb.append('\n');
        }

        return sb.toString();
    }


    private static class CabinRow {

        private final Row left;
        private final Row right;

        private CabinRow(CabinType type) {
            this.left = new Row(type);
            this.right = new Row(type);
        }

        static CabinRow[] forTypeAndSize(CabinType type, int n) {
            CabinRow[] res = new CabinRow[n];
            for (int i = 0; i < n; i++) res[i] = new CabinRow(type);
            return res;
        }

        boolean canFitParty(Party party) {
            return hasAvailable(party.getRequestedSeatTypes());
        }

        private boolean hasAvailable(Iterable<SeatType> types) {
            return left.hasAvailable(types) || right.hasAvailable(types);
        }

        void addParty(Party party) {
            if (!canFitParty(party))
                throw new IllegalStateException("This party cannot be added because these rows are full");
            if (left.hasAvailable(party.getRequestedSeatTypes()))
                left.addParty(party);
            else
                right.addParty(party);
        }

        public String toString() {
            return "LEFT SIDE: \t" + left.toString() + "\nRIGHT SIDE: \t" + right.toString();
        }

        private static class Row {

            private final HashSet<SeatType> availableSeatTypes;
            private final HashMap<SeatType, Person> takenSeats;

            Row(CabinType cabinType) {
                this.availableSeatTypes = new HashSet<>();
                this.takenSeats = new HashMap<>();
                this.setSeats(cabinType);
            }

            public String toString() {
                return "\nAvailable Seats: " + availableSeatTypes +
                        "\nOccupied seats: " + takenSeats;
            }

            private void setSeats(CabinType cabinType) {
                switch (cabinType) {
                    case FIRST:
                        setSeats(SeatType.WINDOW, SeatType.AISLE);
                        break;
                    case ECONOMY:
                        setSeats(SeatType.WINDOW, SeatType.CENTER, SeatType.AISLE);
                        break;
                    default:
                        throw new IllegalArgumentException("Cabin Type Unsupported");
                }
            }

            private void setSeats(SeatType... types) {
                Collections.addAll(availableSeatTypes, types);
            }

            boolean hasAvailable(Iterable<SeatType> types) {
                for (SeatType requested : types)
                    if (!availableSeatTypes.contains(requested)) return false;
                return true;
            }

            void addParty(Party party) {
                if (!hasAvailable(party.getRequestedSeatTypes()))
                    throw new IllegalStateException("This party cannot be added because these rows are full");

                for (SeatType requested : party.getRequestedSeatTypes()) {
                    availableSeatTypes.remove(requested);
                }
                for (Person p : party.getMembers()) {
                    takenSeats.put(p.getSeatPreference(), p);
                }

            }
        }


    }

}

