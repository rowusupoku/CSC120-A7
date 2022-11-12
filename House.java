import java.util.ArrayList;

/** A class representing a house
 * @author Grace Codd
 * @version 11/01/2022
 * References: 
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
*/
public class House extends Building {

  /** List of residents living in the house */
  private ArrayList<String> residents;
  /** True if house has a dining room, false if not */
  private boolean hasDiningRoom;
  /** True if house has a dining room, false if not */
  private boolean hasElevator;


  /* Default constructor */
  public House() {
    this("<Name Unknown>", "<Address Unknown>", 1, false, false);
  }

  /**
   * Constructor calls superclass constructor to assign name, string, and nFloors to House object.
   * Initializes hasDiningRoom class field and initializes an ArrayList stored in residents field
   * @param name the name of the house
   * @param address the address at which the house is located
   * @param nFloors the number of floors the house has
   * @param hasDiningRoom true or false if house has a dining room 
   * @param hasElevator true or false if house has an elevator
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
    super(name, address, nFloors);
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator = hasElevator;
    residents = new ArrayList<String>();
    System.out.println("You have built a house: 🏠");
  }

  /** Accessor for class field hasDiningRoom
   * @return value stored in hasDiningRoom 
   */
  public boolean diningRoom(){
    return hasDiningRoom;
  }

   /** Accessor for class field hasElevator
   * @return value stored in hasElevator 
   */
  public boolean getElevator(){
    return this.hasElevator;
  }

  /** Accessor for number of residents in the house
   * @return number of elements stored in residents
   */
  public int nResidents(){
    return residents.size();
  }

  /** Takes in a String object representing a name and adds that name to the
   * list of residents in the house. 
   * @param name the name of the student moving in*/
  public void moveIn(String name){
    residents.add(name);
    System.out.println(name + " has moved in.");
  }

  /** Takes in String object representing a name and removes corresponding element from list of residents
   * if name can be found in residents
   * @param name the name of the student who is moving out
   * @return The name of the student moving out
   */
  public String moveOut(String name){
    /* Call isResident method. If returns false, print error message */
    if(!isResident(name)){
      System.out.println("Error! " + name + " is not a resident of " + super.getName());
    }
    /* If returns true, remove element from residents */
    else{
      residents.remove(name);
      System.out.println(name + " has moved out.");
    }
    return name;
  }

  /** Checks if a name matches an existing resident of a house
   * @param person the name of a person
   * @return true if name person is an element in residents, false if not
   */
  public boolean isResident(String person){
    // Call contains method, passing person as argument
    return residents.contains(person);
  }

  /* Navigation methods */
  public Building enter() {
    if (activeFloor != -1) {
      throw new RuntimeException("You are already inside this Building.");
    }
    this.activeFloor = 1;
    System.out.println("You are now inside " + this.name + " on the ground floor.");
    return this; // Return a pointer to the current building
  }

  public Building exit() {
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Building. Must call enter() before exit().");
    }
    if (this.activeFloor > 1) {
        throw new RuntimeException("You have fallen out a window from floor #" +this.activeFloor + "!");
    }
    System.out.println("You have left " + this.name + ".");
    this.activeFloor = -1; // We're leaving the building, so we no longer have a valid active floor
    return null; // We're outside now, so the building is null
  }

  @Override
  public void showOptions() {
    System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goUp(n) \n + goDown() \n + goUp(n) \n + goToFloor(n) \n + goToFloor(n, b) \n + moveIn(s) \n + moveOut(s) \n + isResident(s) \n");
  }

  @Override
  public void goToFloor(int floorNum) {
    if (hasElevator == true){
      throw new RuntimeException("Invalid call to goToFloor. This building has an Elevator");
    }
    else{
      if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
      }
      if (floorNum < 1 || floorNum > this.nFloors) {
          throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
      }
      System.out.println("You are now on floor #" + floorNum + " of " + this.name);
      this.activeFloor = floorNum;
    } 
  } 

  /** Overloaded goToFloor Method
    * @param floorNum the number of the floor the user is trying to go to 
    * @param elevator true or false if house has an elevator
    */
    public void goToFloor(int floorNum, boolean elevator) {
      if (getElevator() == true){
        if (this.activeFloor == -1) {
          throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
          throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
      }
      else{
        throw new RuntimeException("Invalid parameter, " + this.name + " does not have an elevator.");
      }
    } 
  
  
    public void goUp() {
      this.goToFloor(this.activeFloor + 1);
    }
  
    /**Overloaded goUp Method
     * @param floorNum the number of the floor the user is trying to go to
     */
    public void goUp(int floorNum) {
      if (getElevator() == true){
        int difference =  floorNum - this.activeFloor;
        this.goToFloor(this.activeFloor + difference, getElevator());
      }
      else {
        throw new RuntimeException("Invalid parameter, " + this.name + " does not have an elevator.");
      }
        
    }
  
    public void goDown() {
      this.goToFloor(this.activeFloor - 1);
    }
  
  /** Overloaded goDown Method
    * @param floorNum the number of the floor the user is trying to go to 
    */
    public void goDown(int floorNum) {
    if (getElevator() == true){
      int difference= this.activeFloor - floorNum;
      this.goToFloor(this.activeFloor - difference, getElevator());
    }
    else{
      throw new RuntimeException("Invalid parameter, " + this.name + " does not have an elevator.");
    }
  }

  public String toString() {
    return this.name + " is a " + this.nFloors + "-story building located at " + this.address + ".";
  }

  /** Main method for testing
   * @param args the command line arguments (ignored)
   */
  public static void main(String[] args) {

    //Instantiate House object
    House newHouse = new House("Grace's House", "102 Lake St", 
                              2, true, false);

    //Prints class's options
    newHouse.showOptions();
    
    //Add residents to house by calling moveIn instance method                         
    newHouse.moveIn("Grace"); // Me
    newHouse.moveIn("Kira"); // My roommate
    newHouse.moveIn("Fiadh"); // My cat 

    //Print list of residents by calling toString method 
    System.out.println(newHouse.residents.toString());

    //Test moveOut method by passing a name that does NOT match a current resident
    newHouse.moveOut("Jordan");
    //Test moveOut method by passing a name that does match a current resident
    newHouse.moveOut("Grace");

    //Print list of residents to see if moveOut method worked
    System.out.println(newHouse.residents.toString());
    System.out.println("There are " + newHouse.nResidents() + " residents living at " + newHouse.getAddress());

    //Test inherited toString method 
    System.out.println(newHouse.toString());

    System.out.println("-----------------------------------");
    System.out.println("Demonstrating enter/exit/navigation");
    System.out.println("-----------------------------------");
    newHouse.enter();
    newHouse.goUp();
    newHouse.goDown();
    newHouse.exit();
  
  }

}