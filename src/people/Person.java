package people;

public class Person {

	private String name;
	private String seatPreference;
	private int[] possibleSeats;
	private boolean alone;
	private boolean isEconomy;
	
	public Person(String n, String p, boolean a, boolean isEcon) {
		setName(n);
		setSeatPreference(p, isEcon);
		setAlone(a);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeatPreference(String seatPreference, boolean isEconomy) {
		this.seatPreference = seatPreference.toUpperCase();	
		if(isEconomy){
			int[] a = {99,99};
			if      (this.seatPreference.equals("WINDOW")){a[0] = 0; a[1] = 5;}
			else if (this.seatPreference.equals("CENTER")){a[0] = 1; a[1] = 4;}
			else if (this.seatPreference.equals("AISLE")) {a[0] = 2; a[1] = 3;}
			setPossibleSeats(a);
		}
		else{
			int[] a = {99,99};
			if      (this.seatPreference.equals("WINDOW")){a[0] = 0; a[1] = 3;}
			else if (this.seatPreference.equals("AISLE")) {a[0] = 1; a[1] = 2;}
			setPossibleSeats(a);
		}
	}
	
	public String getSeatPreference(){
		return this.seatPreference;
	}
	public boolean isAlone() {
		return alone;
	}

	public void setAlone(boolean alone) {
		this.alone = alone;
	}

	
	public boolean isEconomy() {
		return isEconomy;
	}

	public void setEconomy(boolean isEconomy) {
		this.isEconomy = isEconomy;
	}

	public int[] getPossibleSeats() {
		return possibleSeats;
	}

	public void setPossibleSeats(int[] possibleSeats) {
		this.possibleSeats = possibleSeats;
	}

	public String toString(){
		String s = getName() + " || " + "Seating Preference: " + getSeatPreference() + " || " + "Solo Passenger: " + isAlone();
		return s;
	}

}
