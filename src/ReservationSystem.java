import people.Party;
import people.Person;
import people.SeatType;
import plane.CabinType;
import plane.Plane;

public class ReservationSystem {

    public static void main(String[] args) {

        Plane jam = new Plane(2);
        Person w = new Person("windowed person", SeatType.WINDOW);
        Person c = new Person("centered person", SeatType.CENTER);
        Person a = new Person("aisled person", SeatType.AISLE);

        jam.addParty(new Party(CabinType.FIRST, w, a));
        jam.addParty(new Party(CabinType.FIRST, w));
        jam.addParty(new Party(CabinType.ECONOMY, w, a));
        jam.addParty(new Party(CabinType.ECONOMY, w, c));
        jam.addParty(new Party(CabinType.ECONOMY, w, c, a));
        jam.addParty(new Party(CabinType.ECONOMY, c, a));

//        jam.addParty(new Party(CabinType.FIRST, a,a));jam.addParty(new Party(CabinType.FIRST, w,a));jam.addParty(new Party(CabinType.FIRST, w,a));

        System.out.println("\n\n" + jam);


//		Scanner in = null;
//		while(run){
//			try{
//				in = new Scanner(System.in);
//
//				System.out.println("Hello, welcome to TMJ Airlines! \n");
//				System.out.println("Please select an option: \n");
//				System.out.println("1: Add new passengers");
//				System.out.println("2: View Passengers");
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
//						System.out.print("Enter choice: ");
//						int seatingChoice = in.nextInt();
//
//						switch(seatingChoice){
//
//							case 1:{
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
//										String chosenSeat = " ";
//										loop: while(true){
//											System.out.println("Please select a seat preference: \n 1: Window, 2:Aisle? ");
//											System.out.print("Enter choice: ");
//											int seatPref = in.nextInt();
//
//											if(seatPref==1){
//												chosenSeat = "WINDOW";
//												break loop;
//											}
//											else if(seatPref==2){
//												chosenSeat = "AISLE";
//												break loop;
//											}
//											else{
//												System.out.println("Invalid input, please try again!");
//											}
//										}
//
//										tmj.addFirst(new Person(name,chosenSeat, true, false));
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
//										String chosenSeatFP = " ";
//										loop: while(true){
//											System.out.println("Please select a seat preference: \n 1: Window, 2:Aisle? ");
//											System.out.print("Enter choice: ");
//											int seatPref = in.nextInt();
//
//											if(seatPref==1){
//												chosenSeatFP = "WINDOW";
//												break loop;
//											}
//											else if(seatPref==2){
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
//										if(chosenSeatFP.equals("WINDOW"))
//											secondSeatPref = "AISLE";
//										else
//											secondSeatPref = "WINDOW";
//
//										System.out.println("The second passenger has automatically been assigned a "+secondSeatPref+" seat for flying duo in First Class!");
//
//										tmj.addFirst(new Person(name,chosenSeatFP, false, false),new Person(secondName,secondSeatPref, false, false));
//
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
//							case 2:{
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
