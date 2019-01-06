import people.Party;
import people.Person;
import people.SeatType;
import plane.CabinType;
import plane.Plane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ReservationSystem {

    private static void makePersonForParty(Scanner in, Party party) {
        int exit = Integer.MIN_VALUE;
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
                int choice = in.nextInt() - 1;
                party.addPerson(new Person(name, remaining.get(choice)));
                openSeatTypes.remove(remaining.get(choice));

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
                if(CabinType.valueOf(choice).isPresent()){
                     p = new Party(CabinType.valueOf(choice).get());
                }else{
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
//        Person w = new Person("windowed person", SeatType.WINDOW);
//        Person c = new Person("centered person", SeatType.CENTER);
//        Person a = new Person("aisled person", SeatType.AISLE);
//
//        jam.addParty(new Party(CabinType.FIRST, w, a));
//        jam.addParty(new Party(CabinType.FIRST, w));
//        jam.addParty(new Party(CabinType.ECONOMY, w, a));
//        jam.addParty(new Party(CabinType.ECONOMY, w, c));
//        jam.addParty(new Party(CabinType.ECONOMY, w, c, a));
//        jam.addParty(new Party(CabinType.ECONOMY, c, a));
//
//
//        System.out.println("\n\n" + jam);
//        showAddPassengersDialog();

//		Scanner in = null;
//		while(true){
//			try{
//				in = new Scanner(System.in);
//
//				System.out.println("Hello, welcome to JAM Airlines! \n");
//				System.out.println("Please select an option: \n");
//				System.out.println("1: Add new passengers");
//				System.out.println("2: View Seating Chart");
//				System.out.println("3: Exit\n");
//
//				System.out.print("Enter choice: ");
//				int choice = in.nextInt();
//				System.out.println();
//
//				switch(choice){
//					case 1:{
//						System.out.println("Please select an option:");
//						System.out.println("1: First Class seating");
//						System.out.println("2: Economy Class seating");
//
//                        System.out.print("Enter choice: ");
//                        Optional<CabinType> cabinType = CabinType.valueOf(in.nextInt());
//                        if(!cabinType.isPresent()) {
//                            System.out.println("Try again.");
//                            continue;
//                        }
//
//						switch(cabinType.get()){
//
//                            case FIRST:{
//								System.out.println("Please select an option:");
//								System.out.println("1: Solo Passenger");
//								System.out.println("2: Duo Passengers");
//
//								System.out.print("Enter choice: ");
//								int prefChoice = in.nextInt();
//
//								switch(prefChoice){
//									case 1:{
//										System.out.println("Please enter the information: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String name = in.nextLine();
//
//										SeatType chosenSeat;
//										loop: while(true){
//											System.out.println("Please select a seat preference: \n 1: Window, 2:Aisle? ");
//											System.out.print("Enter choice: ");
//											int seatPref = in.nextInt();
//
//											if(seatPref==1){
//												chosenSeat = SeatType.WINDOW;
//												break loop;
//											}
//											else if(seatPref==2){
//												chosenSeat = SeatType.AISLE;
//												break loop;
//											}
//											else{
//												System.out.println("Invalid input, please try again!");
//											}
//										}
//
//										tmj.addParty(new Party(CabinType.FIRST, new Person(name, chosenSeat)));
//										break;
//									}
//									case 2:{
//										System.out.println("Please enter the information: ");
//
//										System.out.println("Please enter the information for the first passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String name = in.nextLine();
//
//										SeatType chosenSeatFP;
//                                        while (true) {
//                                            System.out.println("Please select a seat preference: \n 1: Window, 2:Aisle? ");
//                                            System.out.print("Enter choice: ");
//                                            int seatPref = in.nextInt();
//
//                                            if (seatPref == 1) {
//                                                chosenSeatFP = SeatType.WINDOW;
//                                                break;
//                                            } else if (seatPref == 2) {
//                                                chosenSeatFP = SeatType.AISLE;
//                                                break;
//                                            } else {
//                                                System.out.println("Invalid input, please try again!");
//                                            }
//                                        }
//
//										System.out.println("Please enter the information for the second passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String secondName = in.nextLine();
//
//
//										SeatType secondSeatPref;
//										if(chosenSeatFP.equals(SeatType.WINDOW))
//											secondSeatPref = SeatType.AISLE;
//										else
//											secondSeatPref = SeatType.WINDOW;
//
//										System.out.println("The second passenger has automatically been assigned a "+secondSeatPref+" seat for flying duo in First Class!");
//
//										Party p = new Party(CabinType.FIRST, new Person(name,chosenSeatFP),new Person(secondName,secondSeatPref));
//tmj.addParty(p);
//										break;
//
//									}
//									default:{
//										System.out.println("Error: Invalid choice.");
//									}
//
//								}
//								break;
//							}
//                            case ECONOMY:{
//								System.out.println("Please select an option:");
//								System.out.println("1: Solo Passenger");
//								System.out.println("2: Duo Passengers");
//								System.out.println("3: Trio Passengers");
//
//								System.out.print("Enter choice: ");
//								int prefChoice = in.nextInt();
//
//								switch(prefChoice){
//									case 1:{
//										System.out.println("Please enter the information: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String name = in.nextLine();
//
//										String chosenSeatFP = " ";
//										loop: while(true){
//											System.out.println("Please select a seat preference: \n 1: Window, 2: Center, 3: Aisle? ");
//											System.out.print("Enter choice: ");
//											int seatPref = in.nextInt();
//
//											if(seatPref==1){
//												chosenSeatFP = "WINDOW";
//												break loop;
//											}
//											else if(seatPref==2){
//												chosenSeatFP = "CENTER";
//												break loop;
//											}
//											else if(seatPref==3){
//												chosenSeatFP = "AISLE";
//												break loop;
//											}
//											else{
//												System.out.println("Invalid input, please try again!");
//											}
//										}
//
//										tmj.addEconomy(new Person(name,chosenSeatFP, true, true));
//										break;
//									}
//									case 2: {
//
//										System.out.println("Please enter the information: ");
//
//										System.out.println("Please enter the information for the first passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String name = in.nextLine();
//
//										String chosenSeatFP = " ";
//										loop: while(true){
//											System.out.println("Please select a seat preference: \n 1: Window, 2: Center, 3: Aisle? ");
//											System.out.print("Enter choice: ");
//											int seatPref = in.nextInt();
//
//											if(seatPref==1){
//												chosenSeatFP = "WINDOW";
//												break loop;
//											}
//											else if(seatPref==2){
//												chosenSeatFP = "CENTER";
//												break loop;
//											}
//											else if(seatPref==3){
//												chosenSeatFP = "AISLE";
//												break loop;
//											}
//											else{
//												System.out.println("Invalid input, please try again!");
//											}
//										}
//
//										System.out.println("Please enter the information for the second passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String secondName = in.nextLine();
//
//
//										String secondSeatPref=" ";
//										System.out.println("Passenger 1 has chosen "+chosenSeatFP);
//										if(chosenSeatFP.equals("WINDOW")){
//											System.out.println("You have been assigned center since you're traveling together.");
//											secondSeatPref = "CENTER";
//										}
//										else if(chosenSeatFP.equals("CENTER")){
//											loop: while(true){
//												System.out.println("Please select a seat preference: \n 1: Window, 2:Aisle? ");
//												System.out.print("Enter choice: ");
//												int seatPref = in.nextInt();
//
//												if(seatPref==1){
//													secondSeatPref = "WINDOW";
//													break loop;
//												}
//												else if(seatPref==2){
//													secondSeatPref = "AISLE";
//													break loop;
//												}
//												else{
//													System.out.println("Invalid input, please try again!");
//												}
//											}
//										}
//										else if(chosenSeatFP.equals("AISLE")){
//											System.out.println("you have been assigned center");
//											secondSeatPref = "CENTER";
//										}
//										else{
//											System.out.println("Error: Invalid input.");
//											break;
//										}
//
//										tmj.addEconomy(new Person(name,chosenSeatFP, false, true),new Person(secondName,secondSeatPref, false, true));
//										break;
//									}
//									case 3:{
//										System.out.println("Please enter the information: ");
//
//										System.out.println("Please enter the information for the first passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String name = in.nextLine();
//
//										String chosenSeatFP = " ";
//										loop: while(true){
//											System.out.println("Please select a seat preference: \n 1: Window, 2: Center, 3: Aisle? ");
//											System.out.print("Enter choice: ");
//											int seatPref = in.nextInt();
//
//											if(seatPref==1){
//												chosenSeatFP = "WINDOW";
//												break loop;
//											}
//											else if(seatPref==2){
//												chosenSeatFP = "CENTER";
//												break loop;
//											}
//											else if(seatPref==3){
//												chosenSeatFP = "AISLE";
//												break loop;
//											}
//											else{
//												System.out.println("Invalid input, please try again!");
//											}
//										}
//
//										System.out.println("Please enter the information for the second passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String secondName = in.nextLine();
//
//
//										String chosenSeatSP=" ";
//
//										System.out.println("Passenger 1 has chosen "+chosenSeatFP);
//										if(chosenSeatFP.equals("WINDOW")){
//											loop: while(true){
//												System.out.println("Please select a seat preference: \n 1: Center, 2:Aisle? ");
//												System.out.print("Enter choice: ");
//												int seatPref = in.nextInt();
//
//												if(seatPref==1){
//													chosenSeatSP = "CENTER";
//													break loop;
//												}
//												else if(seatPref==2){
//													chosenSeatSP = "AISLE";
//													break loop;
//												}
//												else{
//													System.out.println("Invalid input, please try again!");
//												}
//											}
//										}
//										else if(chosenSeatFP.equals("CENTER")){
//											loop: while(true){
//												System.out.println("Please select a seat preference: \n 1: Window, 2:Aisle? ");
//												System.out.print("Enter choice: ");
//												int seatPref = in.nextInt();
//
//												if(seatPref==1){
//													chosenSeatSP = "WINDOW";
//													break loop;
//												}
//												else if(seatPref==2){
//													chosenSeatSP = "AISLE";
//													break loop;
//												}
//												else{
//													System.out.println("Invalid input, please try again!");
//												}
//											}
//										}
//										else if(chosenSeatFP.equals("AISLE")){
//
//											loop: while(true){
//												System.out.println("Please select a seat preference: \n 1: Window, 2:Center? ");
//												System.out.print("Enter choice: ");
//												int seatPref = in.nextInt();
//
//												if(seatPref==1){
//													chosenSeatSP = "WINDOW";
//													break loop;
//												}
//												else if(seatPref==2){
//													chosenSeatSP = "CENTER";
//													break loop;
//												}
//												else{
//													System.out.println("Invalid input, please try again!");
//												}
//											}
//										}
//										else{
//											System.out.println("Error: Invalid input");
//											break;
//										}
//										System.out.println("Please enter the information for the third Passenger: ");
//
//										System.out.print("Full name: ");
//										in.nextLine();
//										String thirdName = in.nextLine();
//
//										String thirdSeatPref=" ";
//										System.out.println("Passenger 1 has chosen "+chosenSeatFP);
//										System.out.println("Passenger 2 has chosen "+chosenSeatSP);
//										if((chosenSeatFP.equals("WINDOW")||chosenSeatSP.equals("WINDOW"))&&(chosenSeatFP.equals("CENTER")||chosenSeatSP.equals("CENTER"))){
//											System.out.println("You have been assigned AISLE to be seated with your group.");
//											thirdSeatPref = "AISLE";
//										}
//										else if((chosenSeatFP.equals("CENTER")||chosenSeatSP.equals("CENTER"))&&(chosenSeatFP.equals("AISLE")||chosenSeatSP.equals("AISLE"))){
//											System.out.println("You have been assigned WINDOW to be seated with your group.");
//											thirdSeatPref = "WINDOW";
//										}
//										else if((chosenSeatFP.equals("WINDOW")||chosenSeatSP.equals("WINDOW"))&&(chosenSeatFP.equals("AISLE")||chosenSeatSP.equals("AISLE"))){
//											System.out.println("You have been assigned CENTER to be seated with your group.");
//											thirdSeatPref = "CENTER";
//										}
//										else{
//											System.out.println("Error: Invalid input");
//											break;
//										}
//
//										tmj.addEconomy(new Person(name,chosenSeatFP, false, true),new Person(secondName,chosenSeatSP, false, true),new Person(thirdName,thirdSeatPref, false, true));
//
//										break;
//									}
//									default:{
//										System.out.println("Error: Invalid input");
//									}
//
//									break;
//								}
//
//							}
//						}
//						break;
//					}
//					case 2:{
//						System.out.println(tmj);
//						break;
//					}
//					case 3:{
//						in.close();
//						System.exit(0);
//					}
//					default:{
//						System.out.println("Error: Invalid input.");
//					}
//				}
//			}catch(Exception e){System.out.println("\nError: User input is an illegal argument!!!\n");
//			}
//
//		}
    }

}
