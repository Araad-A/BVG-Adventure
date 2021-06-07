import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();
  public static HashMap<String, Item> itemMap = new HashMap<String, Item>();
  private Parser parser;
  private Room currentRoom;
  private boolean atDoor;
  private Exit door;
  private Inventory inventory;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    try {
      initRooms("Zork\\src\\zork\\data\\rooms.json");
      initItems("Zork\\src\\zork\\data\\items.json");
      currentRoom = roomMap.get("Hallway1-1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    inventory = new Inventory();
    parser = new Parser();
  }

  private void initRooms(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonRooms = (JSONArray) json.get("rooms");

    for (Object roomObj : jsonRooms) {
      Room room = new Room();
      String roomName = (String) ((JSONObject) roomObj).get("name");
      String roomId = (String) ((JSONObject) roomObj).get("id");
      String roomDescription = (String) ((JSONObject) roomObj).get("description");
      String floor = (String) ((JSONObject) roomObj).get("floor");
      boolean isDark = (boolean) ((JSONObject) roomObj).get("isDark");
      room.setFloor(floor);
      room.setDescription(roomDescription);
      room.setRoomName(roomName);
      room.setIsDark(isDark);

      JSONArray jsonExits = (JSONArray) ((JSONObject) roomObj).get("exits");
      ArrayList<Exit> exits = new ArrayList<Exit>();
      for (Object exitObj : jsonExits) {
        String direction = (String) ((JSONObject) exitObj).get("direction");
        String adjacentRoom = (String) ((JSONObject) exitObj).get("adjacentRoom");
        String keyId = (String) ((JSONObject) exitObj).get("keyId");
        Boolean isLocked = (Boolean) ((JSONObject) exitObj).get("isLocked");
        Boolean isOpen = (Boolean) ((JSONObject) exitObj).get("isOpen");
        Exit exit = new Exit(direction, adjacentRoom, isLocked, keyId, isOpen);
        exits.add(exit);
      }
      room.setExits(exits);
      roomMap.put(roomId, room);
    }
  }

  private void initItems(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonItems = (JSONArray) json.get("items");

    for (Object itemObj : jsonItems) {
      int itemType =  ((Long)((JSONObject) itemObj).get("itemType")).intValue();
      int weight = ((Long) ((JSONObject) itemObj).get("weight")).intValue();
      String name = (String) ((JSONObject) itemObj).get("name");
      String room = (String) ((JSONObject) itemObj).get("room");
      Item item = null;
      if(itemType==Item.DEFAULT){
        Boolean isOpenable = (Boolean) ((JSONObject) itemObj).get("isOpenable");
        item = new Item(weight, name, isOpenable);
        itemMap.put(name,item);
      }else if(itemType==Item.KEY){
        String keyId = (String) ((JSONObject) itemObj).get("keyId");
        item = new Key(keyId, name, weight);
        itemMap.put(name,item);
      }else if(itemType==Item.FLASHLIGHT){
        item = new Flashlight(weight, name);
        itemMap.put(name,item);
      }else if(itemType==Item.READABLE){
        String contents = (String) ((JSONObject) itemObj).get("contents");
        item = new ReadableItem(weight, name, contents);
        itemMap.put(name, item);
      }
      roomMap.get(room).getInventory().addItem(item);
    }
  }

  /**
   * Main play routine. Loops until end of play.
   */
  public void play() {
    printWelcome();

    boolean finished = false;
    while (!finished) {
      Command command;
      try {
        command = parser.getCommand();
        finished = processCommand(command);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    System.out.println("Thank you for playing.  Good bye.");
  }

  /**
   * Print out the opening message for the player.
   */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to Bayview Glen!");
    System.out.println("You are a student at BVG, but something doesn't seem right...");
    System.out.println("Type 'help' if you need help.");
    System.out.println();
    System.out.println(currentRoom.longDescription(false));
  }

  /**
   * Given a command, process (that is: execute) the command. If this command ends
   * the game, true is returned, otherwise false is returned.
   */
  private boolean processCommand(Command command) {
    if (command.isUnknown()) {
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help"))
      printHelp();
    else if (commandWord.equals("go")){
      if (!command.hasSecondWord()) {
        // if there is no second word, we don't know where to go...
        System.out.println("Go where?");
      }else{
        String direction = command.getSecondWord();
        goRoom(direction);
      }
    }else if(commandWord.equalsIgnoreCase("n")||commandWord.equalsIgnoreCase("north")){
      goRoom("North");
    }else if(commandWord.equalsIgnoreCase("e")||commandWord.equalsIgnoreCase("east")){
      goRoom("East");
    }else if(commandWord.equalsIgnoreCase("s")||commandWord.equalsIgnoreCase("south")){
      goRoom("South");
    }else if(commandWord.equalsIgnoreCase("w")||commandWord.equalsIgnoreCase("west")){
      goRoom("West");
    }else if(commandWord.equalsIgnoreCase("ne")||commandWord.equalsIgnoreCase("northeast")){
      goRoom("Northeast");
    }else if(commandWord.equalsIgnoreCase("se")||commandWord.equalsIgnoreCase("southeast")){
      goRoom("Southeast");
    }else if(commandWord.equalsIgnoreCase("sw")||commandWord.equalsIgnoreCase("southwest")){
      goRoom("Southwest");
    }else if(commandWord.equalsIgnoreCase("nw")||commandWord.equalsIgnoreCase("northwest")){
      goRoom("Northwest");
    }else if (commandWord.equals("quit")) {
      if (command.hasSecondWord())
        System.out.println("Quit what?");
      else
        return true; // signal that we want to quit
    } else if (commandWord.equals("eat")) {
      System.out.println("Do you really think you should be eating at a time like this?");
    } else if (commandWord.equals("sleep")) {
      System.out.println("You took a nice, long nap. You feel refreshed and ready to continue on your journey.");
    }else if (commandWord.equals("escape")) {
      System.out.println("WHAT ARE YOU DOING? You can't run out of here. Don't give up just yet!!!"); 
    }else if(commandWord.equals("leave")){
      if(atDoor){
        System.out.println(currentRoom.longDescription(false));
        atDoor=false;
      }else{
        System.out.println("I don't know what you mean...");
      }
    } else if(commandWord.equals("use")){
      useItem(command);
    } else if(commandWord.equals("inventory")){
      System.out.println(inventory.listItems());
    } else if(commandWord.equals("take")){
      if(!command.hasSecondWord()){
        System.out.println("Take what?");
      }else{
        takeItem(command);
      }
    } else if(commandWord.equals("read")){
      if(!command.hasSecondWord()){
        System.out.println("Read what?");
      }else{
        readItem(command);
      }
    }
    return false;
  }

  // implementations of user commands:

  /**
   * Print out some help information. Here we print some stupid, cryptic message
   * and a list of the command words.
   */
  private void printHelp() {
    System.out.println("You are lost. You are alone. You wander");
    System.out.println("around at the Bayview Glen upper school");
    System.out.println();
    System.out.println("Your command words are:");
    parser.showCommands();
  }

  public Room getCurrentRoom(){
    return currentRoom;
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(String direction) {

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);
    if (nextRoom == null)
      System.out.println("There is no door!");
    else if(currentRoom.getExit(direction).isLocked()){
      System.out.println("The door is locked. Will you open it or leave?");
      atDoor=true;
      door=currentRoom.getExit(direction);
    }else {
      currentRoom = nextRoom;
      System.out.println(currentRoom.longDescription(false));
    }
  }

  private void useItem(Command command){
    if(!command.hasSecondWord()){
      System.out.println("Use what?");
      return;
    }
    String itemName=command.getSecondWord();
    if(!(inventory.hasItem(itemName)||itemName.equalsIgnoreCase("key"))){
      System.out.println("You don't have this item.");
      return;
    }
    if(atDoor){
      if(itemName.equalsIgnoreCase("key")&&inventory.hasKey(door.getKeyId())){
        door.setLocked(false);
        currentRoom = roomMap.get(door.getAdjacentRoom());
        atDoor=false;
        System.out.println("The door opens. "+currentRoom.longDescription(false));
        if(itemName.equalsIgnoreCase("acid")||itemName.equalsIgnoreCase("instrument")){
          inventory.removeItem(itemName);
          System.out.println("You lost your "+itemName);
        }
      }else{
        System.out.println("You can't use that to open this door.");
      }
      return;
    }
    if(itemName.equalsIgnoreCase("gps")){
      if(inventory.hasItem("Key 7"))
        System.out.println("The GPS is blank.");
      else if(inventory.hasItem("Key 6"))
        System.out.println("The key is on the west side of the school.");
      else if(inventory.hasItem("Key 5"))
        System.out.println("The key is on the north side of the school.");
      else
        System.out.println("The key is on the south side of the school.");
    }
    if(itemName.equalsIgnoreCase("flashlight")){
      if(currentRoom.isDark())
        System.out.println(currentRoom.longDescription(true));
      else
        System.out.println("It is not dark.");
    }
  }

  private void takeItem(Command command){
    String itemName = command.getSecondWord();
    Inventory roomInventory = currentRoom.getInventory();
    if(itemName.equalsIgnoreCase("key")){
      ArrayList<Item> items = roomInventory.getItems();
      for(int i=0;i<items.size();i++){
        if(items.get(i) instanceof Key){
          boolean added = inventory.addItem(items.get(i));
          if(added){
            roomInventory.removeItem(items.get(i).getName());
            System.out.println("You took this "+itemName+".");
          }
          return;
        }
      }
      System.out.println("You can't find this item.");
    }else{
      if(roomInventory.hasItem(itemName)){
        if(!(roomInventory.getItem(itemName) instanceof ReadableItem)){
          boolean added = inventory.addItem(roomInventory.getItem("flashlight"));
          if(added){
            roomInventory.removeItem(itemName);
            System.out.println("You took this "+itemName+".");
          }
        }else{
          System.out.println("You can't take this item.");
        }
      }else{
        System.out.println("You can't find this item.");
      }
    }
  }

  private void readItem(Command command){
    String itemName = command.getSecondWord();
    Inventory roomInventory = currentRoom.getInventory();
    if(roomInventory.hasItem(itemName)&&roomInventory.getItem(itemName) instanceof ReadableItem){
      ReadableItem readable = (ReadableItem)(roomInventory.getItem(itemName));
      readable.read();
      return;
    }
    System.out.println("You can't read this.");
  }
}
