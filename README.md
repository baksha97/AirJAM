# AirJAM

**Software Design Methods**

Original Project Timeline: _January 2017 - May 2017_

*Original Members and Initial Creators*

Team JAM: [Juan Guerrero](https://github.com/juang97), Alfayed Baksh, [Maynard Santos](https://github.com/mayntos)

### Project description
The objective of this assignment involved using Object Oriented Design and Programming in **Java** to create the simulation of an airplane's booking system.

---


#### Revival Period
_January 2019 - TBD_

 I have proposed the reimplementation of the project using updated skills in OOD, OOP, and unit testing. It is my hope that the project can further showcase my skills in these areas. 
 
 Feel free to:
 - Use parts of this project as you see fit under the license. 
 - Contribute.
 
 
### Highlights
 
 Data Abstraction through a public interface:
 - The client solely interacts with the ReservationSystem and Plane.
 - `CabinRow.create()` is package private to prevent objects outside of the place objects from interacting with it.
 
 Encapsulation & Modularity:
 - The majority of the objects contain internals hidden by the compiler, and the public interface provides only the means that can be used to ask the object to modify the data; ensuring integrity.
 - The visible components to the client are certainly important for each other, but the components themselves have been written to easily be passed around inside of the system.
 
 Overriding & Overloading:
 - Various forms of inheritance occur. Overriding is present in many of these situations, for example; the majority of objects have had their default `toString()` method reimplemented in this way.
 - Overloading has been a major factor to the fluidity of the current code.  Some examples include:
    `private void setSeats(SeatType... types)`,
    `public Party(CabinType requestedCabin, Person... people)`, and 
    `public void addPerson(Person... people)`.
 
 StringBuilder:
 - Strings are immutable in Java and concatenation is expensive. `StringBuilder` has been used to make our String creation more efficient. Some examples include: 
``` @Override
     public String toString() { 
         StringBuilder sb = new StringBuilder();
         for (CabinRow cr : cabinRows) {
             sb.append('\n');
             sb.append(cr.toString());
             sb.append('\n');
         }
         return sb.toString();
     }
   ```
 
 Generics:
  - Parameterized methods/classes using generics to accept multiple data types are a great way to implement reusable and flexible code.
  ```
  boolean hasAvailable(Iterable<SeatType> types) {
                  for (SeatType requested : types)
                      if (!availableSeatTypes.contains(requested)) return false;
                  return true;
  } 
  ```
 

 Enums:
    - Vitally used to map the location of objects and the available locations of seats through `HashSets` and `HashMaps`.
    ```
    enum RowSide {LEFT, RIGHT}
    enum SeatType {WINDOW, CENTER, AISLE}
    ```

 Exceptions:
    - Exceptions are important to reveal possible bugs in our program as soon as possible.
   `if (!canAddParty(p)) throw new IllegalArgumentException("Cannot add party to cabin");`

 The Factory Pattern:
    - We can use the Factory Pattern in situations where classes need to create or produce an object, but that exact object may be a bit different depending on the initial product.
    `static CabinRow[] createCabinRows(CabinType type, int n)`
    
 Event-Driven Programming:
    - Evident in the `ReservationSystem`.
    
    
    while (true) {
                System.out.println("Hello, welcome to JAM Airlines! \n");
                System.out.println("Please select an option: \n");
                System.out.println("1: Add new passengers");
                System.out.println("2: View Seating Chart");
                System.out.println("3: Exit\n");
                System.out.print("Enter choice: ");
        ...
    }
    
 
   #### Possible Todo's

 Inheritance & Polymorphism:
 - Creation employee objects which are involved in the airplane simulation.
 - Analyze maintainability and scalability for possible changes in Cabin and Plane.
 
 Unit Testing & J-Unit Testing:
 - Very each component is working as intended throughout the development process.
 
 
 
