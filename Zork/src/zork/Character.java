public class Character {
    private String name;
    private int health;
    private Inventory inventory;

    public Character(String name, int health, int inventoryMaxWeight){
        this.setName(name);
        this.setHealth(health);
        inventory = new Inventory(inventoryMaxWeight);
    }

    public Character(String name, int health){
        this.setName(name);
        this.setHealth(health);
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
