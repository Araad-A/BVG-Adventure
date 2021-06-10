import java.util.ArrayList;

public class Room {

  private String roomName;
  private String description;
  private ArrayList<Exit> exits;
  private String floor;
  private boolean isDark;
  private Inventory inventory;
  private Boss boss;

  public ArrayList<Exit> getExits() {
    return exits;
  }

  public void setExits(ArrayList<Exit> exits) {
    this.exits = exits;
  }
  
  /**
   * Create a room described "description". Initially, it has no exits.
   * "description" is something like "a kitchen" or "an open court yard".
   */
  public Room(String description) {
    this.description = description;
    exits = new ArrayList<Exit>();
  }

  public Room() {
    roomName = "DEFAULT ROOM";
    description = "DEFAULT DESCRIPTION";
    exits = new ArrayList<Exit>();
    inventory = new Inventory();
    boss = null;
  }

  public void addExit(Exit exit) throws Exception {
    exits.add(exit);
  }

  /**
   * Return the description of the room (the one that was defined in the
   * constructor).
   */
  public String shortDescription(boolean flashlightOn) {
    if (!isDark || flashlightOn)
      return "\n" + "Room: " + roomName + "\n\n" + description;
    else
      return "\n" + "Room: " + roomName + "\n\nIt is dark.";
  }

  /**
   * Return a long description of this room, on the form: You are in the kitchen.
   * Exits: north west
   */
  public String longDescription(boolean flashlightOn) {
    if (!isDark || flashlightOn)
      return "\n" + "Room: " + roomName + "\n" + floor + "\n\n" + description + "\n" + itemString() + "\n" + exitString();
    else
      return "\n" + "Room: " + roomName + "\n" + floor + "\n" + exitString() + "\n\nIt is dark.";
  }

  /**
   * Return a string describing the room's exits, for example "Exits: north west
   * ".
   */
  private String exitString() {
    String returnString = "Exits: ";
    for (Exit exit : exits) {
      returnString += exit.getDirection() + " ";
    }

    return returnString;
  }

  private String itemString(){
    String returnString = "";
    for(Item item:inventory.getItems()){
      if(item.getName().substring(0,3).equalsIgnoreCase("key"))
        returnString+="There is a key on a table." + "\n";
      else if(item.getName().equalsIgnoreCase("flashlight"))
        returnString+="There is a flashlight on the ground." + "\n";
      else if(item.getName().equalsIgnoreCase("gps"))
        returnString+="There is a GPS device on a table." + "\n";
      else if(item.getName().equalsIgnoreCase("poster"))
        returnString+="There is a poster on the wall." + "\n";
      else if(item.getName().equalsIgnoreCase("book"))
        returnString+="There are some open books lying around." + "\n";
      else if(item.getName().equalsIgnoreCase("shovel"))
        returnString+="There is a shovel on the stage that was used in a play." + "\n";
      else if(item.getName().equalsIgnoreCase("sword"))
        returnString+="There is a sword on the stage that was used in a play." + "\n";
    }
    return returnString;
  }

  /**
   * Return the room that is reached if we go from this room in direction
   * "direction". If there is no room in that direction, return null.
   */
  public Room nextRoom(String direction) {
    try {
      for (Exit exit : exits) {

        if (exit.getDirection().equalsIgnoreCase(direction)) {
          String adjacentRoom = exit.getAdjacentRoom();

          return Game.roomMap.get(adjacentRoom);
        }

      }
    } catch (IllegalArgumentException ex) {
      System.out.println(direction + " is not a possible direction.");
      return null;
    }

    System.out.println(direction + " is not a possible direction.");
    return null;
  }

  /*
   * private int getDirectionIndex(String direction) { int dirIndex = 0; for
   * (String dir : directions) { if (dir.equals(direction)) return dirIndex; else
   * dirIndex++; }
   * 
   * throw new IllegalArgumentException("Invalid Direction"); }
   */
  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFloor(){
    return floor;
  }

  public void setFloor(String floor){
    this.floor = floor;
  }

  public void setIsDark(boolean isDark){
    this.isDark = isDark;
  }

  public boolean isDark(){
    return isDark;
  }
  public Exit getExit(String direction){
    for (Exit exit : exits) {

      if (exit.getDirection().equalsIgnoreCase(direction)) {
        return exit;
      }

    }
    return null;
  }

  public Inventory getInventory(){
    return inventory;
  }

  public void setBoss(Boss boss){
    this.boss = boss;
  }

  public Boss getBoss(){
    return boss;
  }
}
