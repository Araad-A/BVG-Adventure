public class Character {
    private String name;
    private int health;
    private Inventory inventory;
/**
 * Constructor for the Character class
 * @param name Name of the character
 * @param inventoryMaxWeight Maximum weight that can be held in the inventory
 */
    public Character(String name, int inventoryMaxWeight){
        this.name = name;
        this.health = 100;
        inventory = new Inventory(inventoryMaxWeight);
    }
/**
 * Overloaded constructor without inventoryMaxWeight
 * @param name Name of the character
 */
    public Character(String name){
        this.name = name;
        this.health = 100;
        inventory = new Inventory();
    }
/**
 * 
 * @return the inventory object belonging to this character
 */
    public Inventory getInventory() {
        return inventory;
    }
/**
 * 
 * @return the health of this character
 */
    public int getHealth() {
        return health;
    }
/**
 * Set the health of this character
 * @param health
 */
    public void setHealth(int health) {
        this.health = health;
    }
/**
 * 
 * @return the name of this character
 */
    public String getName() {
        return name;
    }
/**
 * Set the health of this character
 * @param name
 */
    public void setName(String name) {
        this.name = name;
    }
}
