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
    if (item.getWeight() + currentWeight <= maxWeight){
      items.add(item);
      return true;
    }
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

  public Item getItem(String name){
    for(Item item : items){
      if(item.getName().equalsIgnoreCase(name))
        return item;
    }
    return null;
  }

  public String listItems(){
    if(items.size()==0)
      return "Your inventory is empty.";
    String itemList = "Items: ";
    for(Item item:items){
      itemList+=item.getName()+", ";
      itemList = itemList.substring(0,itemList.length()-2);
    }
    return itemList;
  }

  public ArrayList<Item> getItems(){
    return items;
  }

  public boolean hasKey(String keyId){
    for(Item item:items){
      if(item instanceof Key && ((Key)item).getKeyId().equalsIgnoreCase(keyId)){
        return true;
      }
    }
    return false;
  }
}
