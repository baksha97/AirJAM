package plane;

import people.Party;
import people.Person;
import people.SeatType;

import java.util.*;

public class Cabin {
    private final CabinType type;
    private final ArrayList<Party> parties;
    private final CabinRow[] cabinRows;

    Cabin(CabinType type, int size) {
        this.type = type;
        this.parties = new ArrayList<>();
        this.cabinRows = CabinRow.createCabin(type, size);
    }

    public boolean canAddParty(Party party) {
        for (CabinRow row : cabinRows) {
            if (row.canFitParty(party)) return true;
        }
        return false;
    }

    public void addParty(Party party) {
        if (!canAddParty(party)) throw new IllegalArgumentException("Party specified cannot join this cabin.");
        for (CabinRow row : cabinRows) {
            if (row.canFitParty(party)) {
                row.addParty(party);
                parties.add(party);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
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
            this.left = new Row(RowSide.LEFT, type);
            this.right = new Row(RowSide.RIGHT, type);
        }

        static CabinRow[] createCabin(CabinType type, int n) {
            CabinRow[] res = new CabinRow[n];
            Arrays.fill(res, new CabinRow(type));
            return res;
        }

        boolean canFitParty(Party party) {
            Iterable<SeatType> types = party.getRequestedSeatTypes();
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
            StringBuilder sb = new StringBuilder();
            int maxLength = 50;
            String format = "%-" + maxLength + "s\t";
            sb.append(String.format(format, left));
            format = "%" + maxLength + "s\t";
            sb.append(String.format(format, right));
            return sb.toString();
        }

        enum RowSide {LEFT, RIGHT}

        private static class Row {
            private final RowSide side;
            private final CabinType cabinType;
            private final HashSet<SeatType> availableSeatTypes;
            private final HashMap<SeatType, Person> takenSeats;

            Row(RowSide side, CabinType cabinType) {
                this.side = side;
                this.cabinType = cabinType;
                this.availableSeatTypes = new HashSet<>();
                this.takenSeats = new HashMap<>();
                this.setSeats(cabinType);
            }

            //TODO: Find an a more appropriate object oriented design pattern for creating a string for printing
            //the diagram.
            public String toString() {
                StringBuilder sb = new StringBuilder();
                int maxLength = 10;
                String format = "%" + maxLength + "s\t";
                if (side == RowSide.LEFT) {
                    sb.append(String.format(format, getDiagramKey(SeatType.WINDOW)));
                    if (cabinType == CabinType.ECONOMY)
                        sb.append(String.format(format, getDiagramKey(SeatType.CENTER)));
                    sb.append(String.format(format, getDiagramKey(SeatType.AISLE)));
                } else {//right
                    sb.append(String.format(format, getDiagramKey(SeatType.AISLE)));
                    if (cabinType == CabinType.ECONOMY)
                        sb.append(String.format(format, getDiagramKey(SeatType.CENTER)));
                    sb.append(String.format(format, getDiagramKey(SeatType.WINDOW)));
                }

                return sb.toString();
            }

            private String getDiagramKey(SeatType type) {
                return takenSeats.get(type) != null ? "[ X ]" : "[ O ]";
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
                for (SeatType requested : party.getRequestedSeatTypes())
                    availableSeatTypes.remove(requested);
                for (Person p : party.getMembers())
                    takenSeats.put(p.getSeatPreference(), p);
            }
        }


    }

}

