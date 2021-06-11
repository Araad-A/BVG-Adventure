import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {
/**
 * roomMap: map for easier retreival of room objects from strings
 * parser: processes inputs
 * currentRoom: room occupied by the player
 * atDoor: whether the player is in front of a locked exit or not
 * fighting: whether the player is in a bossfight or not
 * currentBoss: the boss being fought
 * door: the locked exit in from of the player
 * player: the player 
 */
  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();
  private Parser parser;
  private Room currentRoom;
  private boolean atDoor;
  private boolean fighting;
  private Boss currentBoss;
  private Exit door;
  private Character player;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    try {
      initRooms("Zork\\src\\zork\\data\\rooms.json");
      initItems("Zork\\src\\zork\\data\\items.json");
      initBosses("Zork\\src\\zork\\data\\bosses.json");
      currentRoom = roomMap.get("Hallway1-5");
    } catch (Exception e) {
      e.printStackTrace();
    }
    parser = new Parser();
    player = new Character("Student", 13);
  }
/**
 * Initializes rooms
 * @param fileName JSON file name
 */
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
        String lockedMessage = (String) ((JSONObject) exitObj).get("lockedMessage");
        Boolean isLocked = (Boolean) ((JSONObject) exitObj).get("isLocked");
        Boolean isCodeLock = (Boolean) ((JSONObject) exitObj).get("isCodeLock");
        Exit exit = new Exit(direction, adjacentRoom, isLocked, keyId, lockedMessage, isCodeLock);
        exits.add(exit);
      }
      room.setExits(exits);
      roomMap.put(roomId, room);
    }
  }
/**
 * Initializes items
 * @param fileName JSON file name
 */
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
        item = new Item(weight, name);
      }else if(itemType==Item.KEY){
        String keyId = (String) ((JSONObject) itemObj).get("keyId");
        item = new Key(keyId, name, weight);
      }else if(itemType==Item.READABLE){
        String contents = (String) ((JSONObject) itemObj).get("contents");
        item = new ReadableItem(weight, name, contents);
      }
      roomMap.get(room).getInventory().addItem(item);
    }
  }
/**
 * Initializes bosses
 * @param fileName JSON file name
 */
  private void initBosses(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonBosses = (JSONArray) json.get("bosses");

    for (Object bossObj : jsonBosses) {
      int type =  ((Long)((JSONObject) bossObj).get("type")).intValue();
      String name = (String) ((JSONObject) bossObj).get("name");
      String room = (String) ((JSONObject) bossObj).get("room");
      String introduction = (String) ((JSONObject) bossObj).get("introduction");
      String win = (String) ((JSONObject) bossObj).get("win");
      String lose = (String) ((JSONObject) bossObj).get("lose");
      String loseRoom = (String) ((JSONObject) bossObj).get("loseRoom");
      Boss boss = null;
      if(type==Boss.TEST){
        String file = (String) ((JSONObject) bossObj).get("fileName");
        boss = new TestBoss(name, introduction, win, lose, loseRoom, file);
      }else if(type==Boss.FIGHT){
        boss = new FightBoss(name, introduction, win, lose, loseRoom);
      }
      roomMap.get(room).setBoss(boss);
    }
  }

  /**
   * Main play routine. Loops until end of play.
   */
  public void play() {
    welcome();

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
    System.out.println("Thank you for playing. Good bye.");
  }

  /**
   * Print out the opening message for the player.
   */
  private void welcome() {
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
    //Restricts action during boss fights
    if(fighting&&"goeatsleepuseleavetakereadenterneseswnwnortheastsoutheastsouthwestnorthwestupdown".indexOf(commandWord)>=0){
      System.out.println("You can't do this when there is a teacher in the room.");
      return false;
    }
    if (commandWord.equals("help"))
      printHelp();
    else if (commandWord.equals("go")){
      if (!command.hasSecondWord()) {
        // if there is no second word, we don't know where to go...
        System.out.println("Go where?");
      }else{
        String direction = command.getSecondWord();
        return goRoom(direction);
      }
    //Movement shortcuts
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
    }else if(commandWord.equalsIgnoreCase("u")||commandWord.equalsIgnoreCase("up")){
      goRoom("Up");
    }else if(commandWord.equalsIgnoreCase("d")||commandWord.equalsIgnoreCase("down")){
      goRoom("Down");
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
      return useItem(command);
    } else if(commandWord.equals("inventory")){ //lists inventory contents
      System.out.println(player.getInventory().listItems());
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
    } else if(commandWord.equals("enter")){ //used to enter codes on code locks
      if(!atDoor||!door.isCodeLock()){
        System.out.println("You can't do that here.");
      }else{
        if(!command.hasSecondWord()){
          System.out.println("Enter what?");
        }else if(command.getSecondWord().equalsIgnoreCase(door.getKeyId())){
          door.setLocked(false);
          atDoor=false;
          System.out.println("The door opens.");
          goRoom(door.getDirection());
        }else{
          System.out.println("Incorrect passcode.");
        }
      }
    }else if(commandWord.equals("workout")){ //increases player inventory max capacity
      if(currentRoom.getRoomName().equals("Prep School Gym")||currentRoom.getRoomName().equals("Upper School Gym")||currentRoom.getRoomName().equals("Fitness Room")){
        player.getInventory().setMaxWeight(player.getInventory().getMaxWeight()+6);
        System.out.println("You got stronger. You can now carry heavier loads.");
      }else{
        System.out.println("You can't do that here.");
      }
    }else if(commandWord.equals("answer")){ //answer questions in test bossfights
      if(fighting&&currentBoss instanceof TestBoss){
        if(!command.hasSecondWord()){
          System.out.println("Answer what?");
        }else if(command.getSecondWord().equalsIgnoreCase("A") || command.getSecondWord().equalsIgnoreCase("B") || command.getSecondWord().equalsIgnoreCase("C") || command.getSecondWord().equalsIgnoreCase("D")){
          answer(command.getSecondWord());
        }else{
          System.out.println("This is not a valid answer.");
        }
      }else{
        System.out.println("There is nothing to answer.");
      }
    }else if(commandWord.equals("attack")){ //attack an enemy in fight bossfights
      if(fighting&&currentBoss instanceof FightBoss){
        if(!command.hasSecondWord()){
          System.out.println("Attack where?");
        }else{
          fight(command);
        }
      }
    }else if(commandWord.equals("block")){ //block an attack in fight bossfights
      if(fighting&&currentBoss instanceof FightBoss){
        if(!command.hasSecondWord()){
          System.out.println("Block where?");
        }else{
          fight(command);
        }
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
  private boolean goRoom(String direction) {

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);
    if (nextRoom == null) //if there is no room in that direction:, print error message
      System.out.println("There is no door!");
    else if(currentRoom.getExit(direction).isLocked()){ //if the door is locked, show locked message, keep track of which exit is ahead
      door=currentRoom.getExit(direction);
      System.out.println(door.getLockedMessage());
      atDoor=true;
    }else { //if there is a valid unlocked room, enter
      currentRoom = nextRoom;
      if(currentRoom.getRoomName().equalsIgnoreCase("outside")){ // end of game, return true to end main loop
        System.out.println("You take a breath of fresh air as you step outside. You have finally made it out of the school alive. Congratulations.");
        return true;
      }
      if(currentRoom.getBoss()!=null){ //if there is a boss, give a shorter description, introduce the boss and keep track of the boss object
        System.out.println(currentRoom.shortDescription(false));
        fighting=true;
        currentBoss = currentRoom.getBoss();
        System.out.println(currentBoss.getIntroduction());
        if(currentBoss instanceof FightBoss&&!player.getInventory().hasItem("sword")){
          System.out.println("You have no weapon to defend yourself, so you flee to a nearby staircase.");
          currentRoom = roomMap.get(currentBoss.getLoseRoom());
          System.out.println(currentRoom.longDescription(false));
          fighting = false;
        }
        return false;
      }
      System.out.println(currentRoom.longDescription(false));
    }
    return false;
  }
/**
 * Use an object specified by the second word of the command
 */
  private boolean useItem(Command command){
    if(!command.hasSecondWord()){
      System.out.println("Use what?");
      return false;
    }
    String itemName=command.getSecondWord();
    if(!(player.getInventory().hasItem(itemName)||itemName.equalsIgnoreCase("key"))){
      System.out.println("You don't have this item.");
      return false;
    }
    if(atDoor){ //if you are at a locked door, you can only use items for the purpose of opening it
      if(door.isCodeLock()){
        System.out.println("You must enter a code to open this door.");
      }else{
        if(itemName.equalsIgnoreCase("key")||itemName.equalsIgnoreCase("shovel")){
          if(player.getInventory().hasKey(door.getKeyId())){
            door.setLocked(false);
            atDoor=false;
            if(door.getKeyId().equalsIgnoreCase("shovel")){
              System.out.println("A path has been cleared.");
              return goRoom(door.getDirection());
            }else{
              System.out.println("The door opens. ");
              return goRoom(door.getDirection());
            }
          }else{
            System.out.println("You don't have a key that unlocks this door.");
          }
        }else{
          System.out.println("You can't use that to open this door.");
        }
      }
      return false;
    }
    if(itemName.equalsIgnoreCase("gps")){ //read the gps for information on the next key
      if(player.getInventory().hasItem("Key 7"))
        System.out.println("The GPS is blank.");
      else if(player.getInventory().hasItem("Key 6"))
        System.out.println("The key is on the west side of the school.");
      else if(player.getInventory().hasItem("Key 5"))
        System.out.println("The key is on the north side of the school.");
      else
        System.out.println("The key is on the south side of the school.");
      return false;
    }
    if(itemName.equalsIgnoreCase("flashlight")){ //used to get more information in dark rooms
      if(currentRoom.isDark())
        System.out.println("You can now see." + "\n"+currentRoom.longDescription(true));
      else
        System.out.println("It is not dark.");
      return false;
    }
    return false;
  }
/**
 * Take (move from room inventory to player inventory) an item specified by the second word of the command
 */
  private void takeItem(Command command){
    String itemName = command.getSecondWord();
    Inventory roomInventory = currentRoom.getInventory();
    if(itemName.equalsIgnoreCase("key")){ //separate if statement for keys that does not require typing out the full key name
      ArrayList<Item> items = roomInventory.getItems();
      for(int i=0;i<items.size();i++){ //iterate through items in the room inventory, then attempt to take the item that is a Key object
        if(items.get(i) instanceof Key){ 
          boolean added = player.getInventory().addItem(items.get(i));
          if(added){ //only transfer items from room to player inventory if addItem returns true (item fits within capacity)
            roomInventory.removeItem(items.get(i).getName());
            System.out.println("You took this "+itemName+".");
          }
          return;
        }
      }
      System.out.println("You can't find this item.");
    }else{
      if(roomInventory.hasItem(itemName)){
        if(!(roomInventory.getItem(itemName) instanceof ReadableItem)){ //cannot take readable items (posters, books)
          boolean added = player.getInventory().addItem(roomInventory.getItem(itemName));
          if(added){
            roomInventory.removeItem(itemName);
            System.out.println("You took the "+itemName+".");
          }
        }else{
          System.out.println("You can't take this item.");
        }
      }else{
        System.out.println("You can't find this item.");
      }
    }
  }
/**
 * Read (return a string contained by) a readable item specified by the second word of the command
 */
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
/**
 * Simulate a fight with a boss, process player input
 */
  private void fight(Command command){
    String move = command.getCommandWord();
    String area = command.getSecondWord();
    int result = 0;
    if(!area.equals("low")&&!area.equals("high")&&!area.equals("mid")&&!area.equals("middle")){
      System.out.println("This isn't a valid area.");
    }
    if(move.equals("attack")){ //converts command into corresponding move int
      if(area.equals("high"))
        result = ((FightBoss)currentBoss).fight(player, 0);
      else if(area.equals("mid")||area.equals("middle"))
        result = ((FightBoss)currentBoss).fight(player, 1);
      else if(area.equals("low"))
        result = ((FightBoss)currentBoss).fight(player, 2);
    } else if(move.equals("block")){
      if(area.equals("high"))
        result = ((FightBoss)currentBoss).fight(player, 3);
      else if(area.equals("mid")||area.equals("middle"))
        result = ((FightBoss)currentBoss).fight(player, 4);
      else if(area.equals("low"))
        result = ((FightBoss)currentBoss).fight(player, 5);
    }
    //process result of the fight
    if(result==0){ //fight continues
      return;
    }else if(result==1){ //player loses, defeat message is shown, moved to different room and bossfight ends
      System.out.println(currentBoss.getLose());
      currentRoom = roomMap.get(currentBoss.getLoseRoom());
      System.out.println(currentRoom.longDescription(false));
      fighting = false;
    }else if(result==2){//player wins, victory message is shown, boss is removed from room and bossfight ends
      System.out.println(currentBoss.getWin());
      currentRoom.removeBoss();
      System.out.println(currentRoom.longDescription(false));
      fighting = false;
    }
  }
/**
 * Uses player input to answer test questions
 */
  public void answer(String secondWord){
    int result = ((TestBoss)currentBoss).test(secondWord);//passes answer into TestBoss class to simulate test question
    if(result==0){//fight continues
      return;
    }else if(result==1){//player loses, defeat message is shown, moved to different room and bossfight ends
      System.out.println(currentBoss.getLose());
      currentRoom = roomMap.get(currentBoss.getLoseRoom());
      System.out.println(currentRoom.longDescription(false));
      fighting = false;
    }else if(result==2){//player wins, victory message is shown, boss is removed from room and bossfight ends
      System.out.println(currentBoss.getWin());
      currentRoom.removeBoss();
      System.out.println(currentRoom.longDescription(false));
      fighting = false;
    }
  }
}
