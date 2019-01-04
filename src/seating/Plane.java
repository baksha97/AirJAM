package seating;
import people.Person;
public class Plane {
	
	private Economy Economy;
    private FirstClass First;

	public Plane() {
		Economy = new Economy();
        First = new FirstClass();
	}
	
	// adding to First Class:	
	public void addFirst(Person soloPassenger){
		First.addPassenger(soloPassenger);
	}
	
	public void addFirst(Person duo1, Person duo2){
		if(duo1.getSeatPreference().equalsIgnoreCase("window") && duo2.getSeatPreference().equalsIgnoreCase("aisle")){
			First.addPassenger(duo1, duo2);
		}
		else if(duo1.getSeatPreference().equalsIgnoreCase("aisle") && duo2.getSeatPreference().equalsIgnoreCase("window")){
			First.addPassenger(duo2, duo1);
		}
		else{
			System.out.println("ERROR: Plane.class cannot add you to First Class because you have entered illegal arguements for seating preference.");
		}
	}
	
	// adding to Economy Class:	
	public void addEconomy(Person soloPassenger){
		Economy.addPassenger(soloPassenger);
	}
	
	public void addEconomy(Person duo1, Person duo2){
		if(duo1.getSeatPreference().equalsIgnoreCase("window") && duo2.getSeatPreference().equalsIgnoreCase("center")){
			Economy.addPassenger(duo1, duo2);
		}
		else if(duo1.getSeatPreference().equalsIgnoreCase("center") && duo2.getSeatPreference().equalsIgnoreCase("window")){
			Economy.addPassenger(duo2, duo1);
		}
		
		else if(duo1.getSeatPreference().equalsIgnoreCase("center") && duo2.getSeatPreference().equalsIgnoreCase("aisle")){
			Economy.addPassenger(duo1, duo2);
		}
		
		else if(duo1.getSeatPreference().equalsIgnoreCase("aisle") && duo2.getSeatPreference().equalsIgnoreCase("center")){
			Economy.addPassenger(duo2, duo1);
		}
		
		else{
			System.out.println("ERROR: Plane.class cannot add you to Economy Class because you have entered illegal arguements for seating preference.");
		}
	}
	
	public void addEconomy(Person trio1, Person trio2, Person trio3){
		Person[] order = new Person[3];
				order[addEconomyTrioOrderAssigner(trio1)] = trio1;
				order[addEconomyTrioOrderAssigner(trio2)] = trio2;
				order[addEconomyTrioOrderAssigner(trio3)] = trio3;
		if(order[0]!=null && order[1]!=null && order[2]!=null){		
				Economy.addPassenger(order[0], order[1], order[2]);
		}
		else{
			System.out.println("ERROR: Plane.class cannot add you to Economy Class because you have entered illegal arguements for seating preference.");
		}
	}
	
	public int addEconomyTrioOrderAssigner(Person a){
		int pref = 0;
		if     (a.getSeatPreference().equalsIgnoreCase("window")){pref = 0;}	
		else if(a.getSeatPreference().equalsIgnoreCase("center")){pref = 1;}	
		else if(a.getSeatPreference().equalsIgnoreCase("aisle")) {pref = 2;}	
		return pref;
	}
	
	//display
	public String toString(){
		System.out.println("Displaying Seating Diagram...");
		First.displaySeats();
		Economy.displaySeats();
		System.out.println("\nDisplaying Seated Passengers...");
		System.out.println("\n [*T-> Together || *A-> Alone || *E-> Empty]");
		First.displayPassengers();
		Economy.displayPassengers();
		System.out.println();
		First.displayGroups();
		Economy.displayGroups();		
		return"";
	}
}
