public class Character {
    private String name;
    private int health;
    private Inventory inventory;

    public Character(String name, int inventoryMaxWeight){
        this.name = name;
        this.health = 100;
        inventory = new Inventory(inventoryMaxWeight);
    }

    public Character(String name){
        this.name = name;
        this.health = 100;
        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
