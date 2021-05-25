import java.util.ArrayList;

public class Inventory {
  private ArrayList<Item> items;
  private int maxWeight;
  private int currentWeight;

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

  public int getMaxWeight() {
    return maxWeight;
  }

  public int getCurrentWeight() {
    return currentWeight;
  }

  public boolean addItem(Item item) {
    if (item.getWeight() + currentWeight <= maxWeight)
      return items.add(item);
    else {
      System.out.println("No room available to add this item!");
      return false;
    }
  }

  public boolean hasItem(String name){
    for(Item item : items){
      if(item.getName().equalsIgnoreCase(name))
        return true;
    }
    return false;
  }
  
  public void removeItem(String name){
    for(int i=0;i<items.size();i++){
      if(items.get(i).getName().equalsIgnoreCase(name))
        items.remove(i);
    }
  }
}
