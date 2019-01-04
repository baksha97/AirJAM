package seating;

import java.util.ArrayList;
import java.util.Arrays;
import people.*;

public class Economy {

	private Person[][] seats;
	private ArrayList<Person[]> groups = new ArrayList<Person[]>();
	
	public Economy(){
		setSeats(new Person[30][6]);
	}
	//.add() methods	
	/**
	 * adds a single Person to the 2D class seating array.
	 * @param soloPerson solo passenger to be added.
	 */
	public void addPassenger(Person soloPerson){
		boolean hasSeat = false;
		outerloop:
		for(int x=0; x<seats.length;x++){
			for(int y=0, z=seats[0].length-1; y<seats[0].length; y++,z--){
				if( soloPerson.isAlone() == true && 
					isPreferred(y, z, soloPerson.getPossibleSeats()) && 
					isSoloSeatEmpty(x,y,z)){ //we do not have to search inward, although we are keeping it consistent with the other .add() methods. 
						if(seats[x][y] == null){
							seats[x][y] = soloPerson;
						}
						else{
							seats[x][z] = soloPerson;
						}
						hasSeat = true;
						break outerloop;
					}
			}
		}
		if(!hasSeat){System.out.println("Error: We cannot find you an empty seat with preferences selected.");}
		else{
			Person[] a = {soloPerson};
			this.groups.add(a);
		}
	}
	
	/**
	 * adds two Person objects to the 2D economy class seating array.
	 * the order of duo1's and duo2's seatPreference must be considered when passing argument. ORDER MUST BE: WINDOW, CENTER <OR> CENTER, AISLE!
	 * @param duo1 Person to be added.
	 * @param duo2 Person to be added.
	 */
	public void addPassenger(Person duo1, Person duo2){
		boolean together = false;
		outerloop:
		for(int x=0; x<seats.length;x++){
			for(int y=0, z=seats[0].length-1; y<seats[0].length-1; y++,z--){
				if( isTogether(duo1, duo2) &&
					isDuoConsecutivePreferred(y, z, duo1, duo2) &&
					isDuoConsecutiveSeatEmpty(x,y,z)){ //must check inverted seating
						if(isDuoLeftConsecutiveSeatEmpty(x,y)){
							seats[x][y] = duo1;
							seats[x][y+1] = duo2;
						}
						else if(isDuoRightConsecutiveSeatEmpty(x,z)){
							seats[x][z] = duo1;
							seats[x][z-1] = duo2;
						}
					together = true;
					break outerloop;
					}
			}
		}
		if(!together){System.out.println("Error: Unable to find 2 seats together...");}
		else{
			Person[] a = {duo1,duo2};
			this.groups.add(a);
		}
	}
	
	/**
	 * adds three Person objects to the 2D economy class seating array. 
	 * the order of trio1, trio2, and trio3's seating preference must be considered. PARAMETER ORDER MUST BE: WINDOW, CENTER, AISLE!
	 * when passing arguments. 
	 * @param trio1 Person to be added.
	 * @param trio2 Person to be added.
	 * @param trio3 Person to be added.
	 */
	public void addPassenger(Person trio1, Person trio2, Person trio3){
		boolean together = false;
		outerloop:
		for(int x=0; x<seats.length;x++){
			for(int y=0, z=seats[0].length-1; y<seats[0].length-2; y++,z--){
				if( isTogether(trio1,trio2,trio3) &&
					isTrioConsecutivePreferred(y, z, trio1, trio2, trio3) &&
					isTrioConsecutiveSeatEmpty(x,y,z)){ // must check inverted seating >>> is a combination of following methods.... 
						if(isTrioLeftConsecutiveSeatEmpty(x,y)){
							seats[x][y] = trio1;
							seats[x][y+1] = trio2;
							seats[x][y+2] = trio3;
						}
						else if(isTrioRightConsecutiveSeatEmpty(x,z)){
							seats[x][z] = trio1;
							seats[x][z-1] = trio2;
							seats[x][z-2] = trio3;
						}
						together = true;
						break outerloop;
					}
			}
		}
		if(!together){System.out.println("Error: Unable to find 3 seats together...");}
		else{
			Person[] a = {trio1,trio2,trio3};
			this.groups.add(a);
		}
	}

	// .add() dependency methods	
	/**
	 * checks whether the current seat matches the Person's seating preference. 
	 * @param y value of the y seat being checked.
	 * @param z value of the z seat being checked.
	 * @param a an array of the Person's seating preference.
	 * @return
	 */
	public boolean isPreferred(int y, int z, int[]a){
		for(int i=0;i<a.length;i++){
			if(a[i] == y){return true;}
		}	
		for(int i=0;i<a.length;i++){
			if(a[i] == z){return true;}
		}	
		return false;
	}
	
	/**
	 * @param x/y/z value of the matrix seats are being checked. (y = into row->  {while}  <-into row = z)
	 * NOTE: the inward check of a solo seat is no required... it has been added for consistency. 
	 * @return boolean whether the row type seat is empty and checks the inverted possibilities as well. 
	 */
	public boolean isSoloSeatEmpty(int x, int y, int z){
		if((seats[x][y] ==null || seats[x][z]==null)){
			return true;
		}
		return false;
	}
	/**
	 * @param x/y/z value of the matrix seats are being checked. (y = into row->  {while}  <-into row = z)
	 * @return boolean whether the row type seat is empty and checks the inverted possibilities as well. 
	 */
	public boolean isDuoConsecutiveSeatEmpty(int x, int y, int z){
		if(((seats[x][y] == null && seats[x][y+1] == null) || (seats[x][z] == null && seats[x][z-1] == null))){
			return true;
		}
		return false;
	}
	/**
	 * @param x/y/z value of the matrix seats are being checked. (y = into row->  {while}  <-into row = z)
	 * @return boolean whether the row type seat is empty and checks the inverted possibilities as well. 
	 */
	public boolean isTrioConsecutiveSeatEmpty(int x, int y, int z){
		if(((seats[x][y] == null && seats[x][y+1] == null && seats[x][y+2] == null) || (seats[x][z] == null && seats[x][z-1] == null && seats[x][z-2] == null))){
			return true;
		}
		return false;
	}
	
	/**
	 * @param x/y/z value of the matrix seats are being checked and compared to the preferred seat. (y = into row->  {while}  <-into row = z)
	 * >>>>>> using the y-value, we go in from the left <<<>>> using the z-value, we go in from the right >>>>>
	 * @return boolean whether the row type seat is a match and checks the inverted possibilities as well. 
	 */
	public boolean isDuoConsecutivePreferred(int y, int z, Person duo1, Person duo2){
		if(	isPreferred(y, z, duo1.getPossibleSeats()) &&
			isPreferred(y+1, z-1, duo2.getPossibleSeats())){
			return true;
		}
		else{
			return false;
		}
	}	
	public boolean isTrioConsecutivePreferred(int y, int z, Person trio1, Person trio2, Person trio3){
		if(	isPreferred(y, z, trio1.getPossibleSeats()) &&
			isPreferred(y+1, z-1, trio2.getPossibleSeats()) &&
			isPreferred(y+2, z-2, trio3.getPossibleSeats())){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * @param x/y/z value of the matrix seats are being checked. (y = into row->  {while}  <-into row = z)
	 * >>>>>> using the y-value, we go in from the left <<<>>> using the z-value, we go in from the right >>>>>
	 * @return boolean whether the row type seat is empty and checks the inverted possibilities as well. 
	 */
	public boolean isDuoLeftConsecutiveSeatEmpty(int x, int y){
		if(((seats[x][y] == null && seats[x][y+1] == null))){
			return true;
		}
		return false;
	}
	public boolean isDuoRightConsecutiveSeatEmpty(int x, int z){
		if(((seats[x][z] == null && seats[x][z-1] == null))){
			return true;
		}
		return false;
	}

	/**
	 * @param x/y/z value of the matrix seats are being checked. (y = into row->  {while}  <-into row = z)
	 * >>>>>> using the y-value, we go in from the left <<<>>> using the z-value, we go in from the right >>>>>
	 * @return boolean whether the row type seat is empty and checks the inverted possibilities as well. 
	 */
	public boolean isTrioLeftConsecutiveSeatEmpty(int x, int y){
		if(((seats[x][y] == null && seats[x][y+1] == null && seats[x][y+2] == null))){
			return true;
		}
		return false;
	}
	public boolean isTrioRightConsecutiveSeatEmpty(int x, int z){
		if(((seats[x][z] == null && seats[x][z-1] == null && seats[x][z-2] == null))){
			return true;
		}
		return false;
	}
	
	
	/**
	 * @param checks to make sure Person a & b are not set as passengers traveling alone.
	 * @return boolean whether or not they're traveling together
	 */
	public boolean isTogether(Person a, Person b){
		if(a.isAlone()==false && b.isAlone()==false){
			return true;
		}
		return false;
	}
	/**
	 * @param checks to make sure Person a & b & c are not set as passengers traveling alone.
	 * @return boolean whether or not they're traveling together
	 */
	public boolean isTogether(Person a, Person b, Person c){
		if(a.isAlone()==false && b.isAlone()==false && c.isAlone()==false){
			return true;
		}
		return false;
	}	

	// basic class set/get methods.
	/**
	 * returns the 2D array of seats.
	 * @return returns the 2D array of seats.
	 */
	public Person[][] getSeats() {return seats;}
	/**
	 * assigns an array of 30 rows/6 columns to the seats variable.  
	 * @param seats the 2D array to be assigned. 
	 */
	public void setSeats(Person[][] econSeats) {this.seats = econSeats;}
	/**
	 * nested for loops which iterates through the 2D seating array and print a symbol
	 * depending on whether the seat is occupied. 
	 */
	
	// display methods
	public void displaySeats(){
		String symbol = "X";
		System.out.println("\n-----------------------------");
		System.out.println("ECONOMY CLASS SEATING DISPLAY");
		System.out.println("-----------------------------\n");
		for(int i = 0; i < 30; i++)
		{
			if(i == 9 || i == 20){System.out.print("<<EXIT"); System.out.printf(" %20s ", "EXIT>>");System.out.println();} //determines when to print 
			
			for(int j = 0; j < 6; j++)
			{
				
				if(seats[i][j] == null) {symbol = "O";} //if there is NO person in this 2D position, print an O for "Open"
				else {symbol = "X";} //if there is a person in this 2D position, print an X for uh..."Occupied".
				
				System.out.printf(" %s ", symbol);
				if(j == 2) {System.out.print("\t");} //j==2 because: [W C A    \t    A C W]
				if(j < 2 || (j >= 3 && j < 5)) {System.out.print("|");} //[0 | 1 | 2    \t    3 | 4 | 5]
				else if(j >= 5) {System.out.println("\n---------------------------");} //once the a row is finished printing, separated by this line.
			}
		}
	}
	/**
	 * prints the passenger groups.
	 */
	public void displayGroups(){
		System.out.println("Displaying grouped passengers in Economy Class:");
		for(int i=0; i<this.groups.size(); i++){
			System.out.println("--------------------");
			System.out.println("Input Group #"+ (i+1));
			System.out.println(Arrays.toString(groups.get(i)).replace(",", "\n>and\n").replace("[", "START:\n ").replace("]","\nEND.\n"));
		}
	}
	public void displayPassengers(){
		for(int x=0; x<seats.length;x++){
			System.out.println("Row: "+ (x+1));
			for(int y=0; y<seats[0].length; y++){
				String status = "";
				try{
					if(seats[x][y].isAlone()){status = "(A)";} 
					else{status = "(T)";}
					String name = seats[x][y].getName();
					if(y == 2 && name.length() > 5) {System.out.print(name + status + " \t\t");}
					else if(y == 2) {System.out.print(name + status + "\t\t");}
					else if(y > 2){System.out.printf("%-1s ", name + status);}
					else{{System.out.print( name+ status + " ");}}
				}
				catch(NullPointerException e){
					if(y == 2) {System.out.print("Empty(E)\t\t");}
					else{System.out.print("Empty(E) ");}
				}
			}
			System.out.println();
		}
	}
}
