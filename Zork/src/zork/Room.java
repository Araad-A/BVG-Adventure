import java.util.ArrayList;

public class Room {

  private String roomName;
  private String description;
  private ArrayList<Exit> exits;
  private String floor; //a string that describes what floor the room is on
  private boolean isDark; //whether the room is dark or not
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
/**
 * Adds an exit to the room
 * @param exit The Exit object being added
 */
  public void addExit(Exit exit) throws Exception {
    exits.add(exit);
  }

  /**
   * Return a short description of this room, consisting of: room name, floor, description text
   * If flashlight is off and it is dark, only show room name and floor, and specify that it is dark
   */
  public String shortDescription(boolean flashlightOn) {
    if (!isDark || flashlightOn)
      return "\n" + "Room: " + roomName + "\n" + floor + "\n\n" + description;
    else
      return "\n" + "Room: " + roomName + "\n" + floor + "\n\nIt is dark.";
  }

  /**
   * Return a long description of this room, consisting of: room name, floor, description text, list of items in the room, list of exits from the room
   * If flashlight is off and it is dark, only show room name, floor, and exits, and specify that it is dark
   * @param flashlightOn
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
/**
 * 
 * @return A string describing items that can be found in the room
 */
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
/**
 * 
 * @return the name of the room
 */
  public String getRoomName() {
    return roomName;
  }
/**
 * Set the name of the room
 * @param roomName
 */
  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }
/**
 * 
 * @return a string containing the description of the room
 */
  public String getDescription() {
    return description;
  }
/**
 * Set the string containing the description of the room
 * @param description
 */
  public void setDescription(String description) {
    this.description = description;
  }
/**
 * 
 * @return a string describing which floor the room is on
 */
  public String getFloor(){
    return floor;
  }
/**
 * Set the string describing which floor the room is on
 * @param floor
 */
  public void setFloor(String floor){
    this.floor = floor;
  }
/**
 * Set whether the room is dark or not
 * @param isDark
 */
  public void setIsDark(boolean isDark){
    this.isDark = isDark;
  }
/**
 * 
 * @return whether the room is dark or not
 */
  public boolean isDark(){
    return isDark;
  }
  /**
   * 
   * @param direction the direction of the exit to be returned
   * @return The exit of this room in the direction that has been passed in
   */
  public Exit getExit(String direction){
    for (Exit exit : exits) {

      if (exit.getDirection().equalsIgnoreCase(direction)) {
        return exit;
      }

    }
    return null;
  }
/**
 * 
 * @return the room's Inventory object
 */
  public Inventory getInventory(){
    return inventory;
  }
/**
 * Set the Boss belonging to this room
 * @param boss
 */
  public void setBoss(Boss boss){
    this.boss = boss;
  }
/**
 * 
 * @return the Boss object belonging to this room
 */
  public Boss getBoss(){
    return boss;
  }
/**
 * Remove the Boss object from this room
 */
  public void removeBoss(){
    boss = null;
  }
}
