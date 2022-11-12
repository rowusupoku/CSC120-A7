/** A class representing a Cafe. Cafe class has an "is-a" relationship with Building class
 * @author Grace Codd
 * @version 11/01/2022
*/
public class Cafe extends Building{

    /** The amount of coffee in stock in ounces */
    private int nCoffeeOunces;
    /** The amount of sugar packets in stock */
    private int nSugarPackets;
    /** The amount of servings of cream in stock */
    private int nCreams;
    /** The amount of cups in stock */
    private int nCups;


    /* Default constructor */
    public Cafe() {
        this("<Name Unknown>", "<Address Unknown>", 1);
    }

    /** Constructor assigns sets values to class fields and calls Building superclass
     * constructor to assign name, address, and nFloors fields to the Cafe object.
     * @param name the name of the cafe
     * @param address the address at which the cafe is located
     * @param nFloors the number of floors the cafe has
     */
    public Cafe(String name, String address, int nFloors){
        //Call superclass constructor passing name, address, and nFloors as args
        super(name, address, nFloors);
        //Assign set values to class fields
        nCoffeeOunces = 200;
        nSugarPackets = 300;
        nCreams = 100;
        nCups =  400;
        System.out.println("You have built a cafe: ☕");
    }


    /** Constructor that sets values to class fields and calls Building superclass
     * constructor to assign name, address, and nFloors coffee ounces, sugar packets
     * creams and cup fields to the Cafe object.
     * @param name the name of the cafe
     * @param address the address at which the cafe is located
     * @param nFloors the number of floors the cafe has
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        //Call superclass constructor passing name, address, and nFloors as args
        super(name, address, nFloors);
        if (name != null) { this.name = name; }
        if (address != null) { this.address = address; } 
        if (nFloors < 1) {
            throw new RuntimeException("Cannot construct a building with fewer than 1 floor.");
        }
        this.nFloors = nFloors;
        System.out.println("You have built a cafe: ☕");
    }

    /** Compares order to inventory and restocks inventory if necessary before selling product. 
     * Removes product sold from inventory. Decrements cups in inventory by one.
     * @param size ounces of coffee sold
     * @param nSugarpackets number of sugar packets in order
     * @param nCreams number of servings of cream in order
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams){
        /* If size, nSugarPackets, or nCreams are greater than what is stored in backstock, call restock method
        and pass 20 as arguments for each field*/
        if(size > nCoffeeOunces || nSugarPackets > this.nSugarPackets || nCreams > this.nCreams){
            restock(20, 20, 20, 20);
            }
        nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        nCups -= 1;
    }

    @Override
    public void showOptions() {
      System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown() \n + goToFloor(n) \n + sellCoffee(n, n, n, n) \n + restock(n, n, n, n) \n");
    }
     
    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
          throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
          throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
        if (floorNum != 1){
            System.out.println("You are now on floor #" + floorNum + " of " + this.name +". It is employees only!");
        }
        else{
            System.out.println("You are now on floor #" + floorNum + " of " + this.name+"." );
        }
        this.activeFloor = floorNum;
      } 
    
      public void goUp() {
        this.goToFloor(this.activeFloor + 1);
      }
    
      public void goDown() {
        this.goToFloor(this.activeFloor - 1);
      }
    
      public String toString() {
        return this.name + " is a " + this.nFloors + "-story building located at " + this.address + ".";
      }

    /** Increases the value stored in each class field to "restock" inventory
     * @param nCoffeeOunces number of ounces of coffee to add to inventory
     * @param nSugarPackets number of sugar packets to add to inventory
     * @param nCreams number of servings of cream to add to inventory
     * @param nCups number of cups to add to inventory
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }

    /** Displays the value of each element left in inventory */
    public void printInventory(){
        System.out.println("Coffee backstock = " + nCoffeeOunces + " ounces");
        System.out.println("Sugar backstock = " + nSugarPackets + " packets");
        System.out.println("Cream backstock = " + nCreams + " servings");
        System.out.println("Cups backstock = " + nCups + " cups");
    }
    
    /** Main method for testing */
    public static void main(String[] args) {
        Cafe myCafe = new Cafe("Grace's Cafe", "228 Random Street", 2);
        myCafe.sellCoffee(16, 1, 1);

        //Prints class's options
        myCafe.showOptions();
        myCafe.printInventory();

    }
    
}