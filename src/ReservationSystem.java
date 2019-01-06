import people.Party;
import people.Person;
import plane.CabinType;
import plane.Plane;
import plane.SeatType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ReservationSystem {

    public static void main(String[] args) {

        Plane jam = new Plane(3);
        System.out.println(jam);

        Scanner in = new Scanner(System.in);


        while (true) {
            System.out.println("Hello, welcome to JAM Airlines! \n");
            System.out.println("Please select an option: \n");
            System.out.println("1: Add new passengers");
            System.out.println("2: View Seating Chart");
            System.out.println("3: Exit\n");

            System.out.print("Enter choice: ");
            int choice = in.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    makePartyForPlane(in, jam);
                    break;
                case 2:
                    System.out.println(jam);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void makePersonForParty(Scanner in, Party party) {
        int exit = 1;
        while (!party.getOpenSeatTypes().isEmpty() && !party.isFull() && exit != -1) {
            try {
                System.out.println("Please enter your name: ");
                String name = in.next();

                System.out.println("Please choose a seating option: ");
                HashSet<SeatType> openSeatTypes = party.getOpenSeatTypes();
                ArrayList<SeatType> remaining = new ArrayList<>(openSeatTypes);

                for (int i = 0; i < remaining.size(); i++) {
                    System.out.println((i + 1) + ": " + remaining.get(i));
                }

                System.out.println("Else exit to main menu.");
                int choice = in.nextInt() - 1;
                if (remaining.get(choice) != null) {
                    party.addPerson(new Person(name, remaining.get(choice)));
                    openSeatTypes.remove(remaining.get(choice));
                } else {
                    break;
                }


                System.out.println("Current party: " + party);

                System.out.println("If party is complete, enter -1, else enter any number");
                exit = in.nextInt();
            } catch (Exception e) {
                System.out.println("Try again.");
            }
        }
        System.out.println("Party completed.");
    }

    private static void makePartyForPlane(Scanner in, Plane plane) {
        while (true) {
            try {
                System.out.println("Please select cabin type:");
                System.out.println("1: First Class");
                System.out.println("2: Economy");
                System.out.println("Else exit to main menu.");

                int choice = in.nextInt();
                Party p;
                if (CabinType.valueOf(choice).isPresent()) {
                    p = new Party(CabinType.valueOf(choice).get());
                } else {
                    break;
                }

                makePersonForParty(in, p);

                if (plane.canAddParty(p)) {
                    plane.addParty(p);
                    System.out.println(plane);
                } else System.out.println("Unable to add you to the cabin.");
            } catch (Exception e) {
                System.out.println("Try again.");
            }
        }
    }


}
