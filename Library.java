import java.util.Hashtable;

/** A class representing a Library 
 * 
 * References:
 * https://www.javacodeexamples.com/print-hashtable-in-java-example/3154
 * @author Grace Codd
 * @version 11/01/2022
*/

public class Library extends Building{
  /** True if house has a dining room, false if not */
  private boolean hasElevator;

  /** Collection of books in the library stored as a Hashtable with key being book title 
   * and value being true or false */
  private Hashtable<String, Boolean> collection;

  /* Default constructor */
  public Library() {
    this("<Name Unknown>", "<Address Unknown>", 1, false);
  }

  /** Constructor calls superclass constructor to assign name, string, and nFloors to
   * Library object. Initializes a Hashtable and stores in class field collection
   * @param name the name of the library
   * @param address the address at which the library is located
   * @param floors the number of floors in the library
   * @param hasElevator true or false if house has an elevator
   */
  public Library(String name, String address, int nFloors, boolean hasElevator) {
    super(name, address, nFloors);
    collection = new Hashtable<>();
    this.hasElevator = hasElevator;
    System.out.println("You have built a library: 📖");
  }

  /** Accessor for class field hasElevator
   * @return value stored in hasElevator 
   */
  public boolean getElevator(){
    return this.hasElevator;
  }

  /** Adds a book to library's collection by calling put method from Hashtable class. Sets 
   * value as true so book is available once added to collection.
   * @param title the title of the book added to colelction
   */
  public void addTitle(String title){
    //Call put method from Hashtable class, passing title and "true" value as args
    collection.put(title, true);
  }

  /** Checks if book is in library's collection and if so, removes book from library's collection
   * @param title the title of book to be removed from collection
   * @return The title of the book removed 
   */
  public String removeTitle(String title){
    /* Call containsTitle method, passing title as arg. If containsTitle returns true,
    * call remove instance method, passing title as arg to remove title from collection */
    if(containsTitle(title)){
      collection.remove(title);
    }
    // If title is not found in collection, print error message
    else
      System.out.println("Error! " + title + " does not exist in the collection");
    return title;
  }

  /** Checks if book is available and replaces value stored at title key with false if available. 
   * @param title the name of the book to be checked out
  */
  public void checkOut(String title){
    /*Call isAvailable method, passing title as arg. If isAvailable returns true,
    * call replace instance method to change value stored at title key to "false"*/
    if(isAvailable(title)){
      collection.replace(title, false);
    }
    /* If title is not available, print error message */
    else
      System.out.println("Error! " + title + " is not available." );
  }

  /** Checks if book is checked out and changes value stored at title key to "true" to return book
   * @param title the name of the book to be returned
   */
  public void returnBook(String title){
    /* Call isAvaibalbe method, passing title as arg. If isAvailable returns false,
     * call replace instance method to change value stored at title key to "true"
     */
    if(!isAvailable(title)){
      collection.replace(title, true);
    }
    /* If title has not already been checked out, print error message */
    else
      System.out.println("Error! " + title + " cannot be returned.");
  }

  /** Checks to see if a title is part of the library's collection
   * @param title the name of the book to search collection for
   * @return True if title is found in collection, false if not
  */
  public boolean containsTitle(String title){
    /* Call containsKey instance method, passing title as arg. If containsKey returns true,
     * this means this title can be found in the collection.
     */
    if(collection.containsKey(title)){
      return true;
    }
    else
      return false;
  }

  /** Checks if a title is available to be checked out from the library
   * @param title the name of the book to check availability
   * @return True if title is available for checkout, false if not.
   */
  public boolean isAvailable(String title){
    /* Call get instance method, passing title as arg. get method will return the value (true or false)
     * stored at the title key. */
    if(collection.get(title) == true){
      return true;
    }
    else
      return false;
  }

  // This method overrides showOptions() of Parent class Building
  @Override
  public void showOptions() {
    System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goUp(n) \n + goDown() \n + goUp(n) \n  + goToFloor(n) \n  + goToFloor(n, b) \n + addTitle(s) \n + removeTitle(s) \n + checkOut(s) \n + returnBook(s) \n + containsTitle(s) \n + isAvailable(s) \n + printCollection(s) \n");
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
  public void goToFloor(int floorNum) {
    if (getElevator() == true){
      throw new RuntimeException("Invalid call to goToFloor. " + this.name + " has an elevator, you must provide parameter. ");
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

  /** Prints every title in colleciton and whether or not the title is available */
  public void printCollection(){

    System.out.println("Title \t\t\t" + "Status");
    System.out.println("-------------------------------------");

    /* Call entrySet method to return a Set of elements in collection. Use forEach method to 
     * print out title and availability status for each element.
     * Reference for this function: https://www.javacodeexamples.com/print-hashtable-in-java-example/3154
     */
    collection.entrySet().forEach( entry -> 
                                    {System.out.print(entry.getKey() + "\t\t\t"); //Print title
                                      //If value is true, print "Available"
                                      if(entry.getValue() == true){
                                        System.out.println("Available");
                                      }
                                      //If value is not true, print "Not avalibale"
                                      else
                                        System.out.println("Not Available");
                                    });
  }

  /** Main method for testing
   * @param args the command line arguments (ignored)
   */
  public static void main(String[] args) {

    //Create instance of Library class
    Library myLibrary = new Library("Neilson", "7 Neilson Drive", 4, true);

    //Add titles to collection by calling addTitle method
    myLibrary.addTitle("Twilight");
    myLibrary.addTitle("Macbeth");
    myLibrary.addTitle("Little Women");

    myLibrary.printCollection();

    //Check out a book by calling checkOut method
    myLibrary.checkOut("Little Women");

    //Prints class's options
    myLibrary.showOptions();


    //Print collection
    myLibrary.printCollection();

    //Try to check out Little Women again
    myLibrary.checkOut("Little Women");

    System.out.println("-----------------------------------");
    System.out.println("Demonstrating enter/exit/navigation");
    System.out.println("-----------------------------------");
    myLibrary.enter();
    //myLibrary.goToFloor(3, true);
    //myLibrary.goToFloor(3, false);
    //myLibrary.goUp();
    //myLibrary.goUp(3);
    //myLibrary.goDown();
    //myLibrary.goDown(1);
    myLibrary.exit();
    
  }
  
}
