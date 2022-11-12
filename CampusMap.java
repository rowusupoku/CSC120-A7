import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
        //building parent class
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Sabin-Reed", "44 College Ln, Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Wright Hall", "5 Chapin Way, Northampton, MA 01063", 3));

        //Library subclass
        myMap.addBuilding(new Library("Neilson Library", "7 Neilson Drive", 4, true));
        myMap.addBuilding(new Library("Josten Library", "Mendenhall Center, 122 Green St, Northampton, MA 01063", 1, false));
        myMap.addBuilding(new Library("Hillyer Art Library", "20 Elm St, Northampton, MA 01063", 3, false));

        //Cafe Subclass
        myMap.addBuilding(new Cafe("Campus Cafe", "100 Elm St, Northampton, MA 01063", 1));
        myMap.addBuilding(new Cafe("Compass Cafe", "7 Neilson Drive, Northampton, MA 01063", 2));

        //House Subclass
        myMap.addBuilding(new House("Emerson House", "3 Paradise Road, Northampton, MA 01063", 4, true, false));
        myMap.addBuilding(new House("Jordan House", "1 Paradise Road, Northampton, MA 01063", 4, false, false));
        myMap.addBuilding(new House("Cutter House", "1 Henshaw Ave, Northampton, MA 01063", 4, true, true));

        System.out.println(myMap);
    }
    
}
