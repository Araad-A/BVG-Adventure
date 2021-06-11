import java.util.ArrayList;
/**
 * Stores items and processes them with various methods
 */
public class Inventory {
  private ArrayList<Item> items;
  private int maxWeight;
  private int currentWeight;
/**
 * Constructor for the class Inventory
 * @param maxWeight Maximum weight capacity of inventory
 */
  public Inventory(int maxWeight) {
    this.items = new ArrayList<Item>();
    this.maxWeight = maxWeight;
    this.currentWeight = 0;
  }

  public Inventory() {
    this.items = new ArrayList<Item>();
    this.maxWeight = Integer.MAX_VALUE;
    this.currentWeight = 0;
  }
/**
 * 
 * @return max weight capacity of inventory
 */
  public int getMaxWeight() {
    return maxWeight;
  }
/**
 * Set max weight capacity of inventory
 * @param maxWeight
 */
  public void setMaxWeight(int maxWeight){
    this.maxWeight = maxWeight;
  }
/**
 * 
 * @return current total weight of items contained in inventory
 */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * adds an Item to the inventory if it can fit based on current weight
   * 
   * @param item, an item object
   * @return true if the item is succesfully added, false otherwise
   */
  public boolean addItem(Item item) {
    if (item.getWeight() + currentWeight <= maxWeight){
      items.add(item);
      currentWeight+=item.getWeight();
      return true;
    }
    else {
      System.out.println("You're not strong enough to carry this item!");
      return false;
    }
  }

  /**
   * checks if the inventory already contains an item
   * 
   * @param name, the name of the item
   * @return true if the inventory has the item, false otherwise
   */
  public boolean hasItem(String name){
    for(Item item : items){
      if(item.getName().equalsIgnoreCase(name))
        return true;
    }
    return false;
  }
  
  /**
   * removes and Item from the items ArrayList
   * Given the name of the Item, and removes it 
   * 
   * @param name, the name of the item
   */
  public void removeItem(String name){
    for(int i=0;i<items.size();i++){
      if(items.get(i).getName().equalsIgnoreCase(name))
        items.remove(i);
    }
  }

  /**
   * based on the name, finds an item and returns it
   * 
   * @param name, the name of the item
   * @return the item object if it is in the arraylist, null otherwise
   *
   */
  public Item getItem(String name){
    for(Item item : items){
      if(item.getName().equalsIgnoreCase(name))
        return item;
    }
    return null;
  }

  /**
   * Lists all the items in the item ArrayList
   * 
   * @return a string itemList containing a list of all items 
   */
  public String listItems(){
    if(items.size()==0)
      return "Your inventory is empty.";
    String itemList = "Items: ";
    for(Item item:items){
      itemList+=item.getName()+", ";
    }
    itemList = itemList.substring(0,itemList.length()-2);
    return itemList;
  }
/**
 * 
 * @return the ArrayList of Item objects
 */
  public ArrayList<Item> getItems(){
    return items;
  }

  /**
   * Checks to see if the items ArrayList has the appropiate key
   * 
   * @param keyId, the ID of a key object
   * @return true if the items ArrayList has the key, false otherwise
   */
  public boolean hasKey(String keyId){
    for(Item item:items){
      if(item instanceof Key && ((Key)item).getKeyId().equalsIgnoreCase(keyId)){
        return true;
      }
    }
    return false;
  }
}
