public class Exit extends OpenableObject {
    private String direction;
    private String adjacentRoom;
    private String lockedMessage;
    private boolean isCodeLock;
  
    public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId) {
      super(isLocked, keyId);
      this.direction = direction;
      this.adjacentRoom = adjacentRoom;
      this.lockedMessage = "The door is locked. Will you open it or leave?";
      this.isCodeLock = false;
    }

    public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId, String lockedMessage, boolean isCodeLock) {
      super(isLocked, keyId);
      this.direction = direction;
      this.adjacentRoom = adjacentRoom;
      this.lockedMessage = lockedMessage;
      this.isCodeLock = isCodeLock;
    }

    public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId, String lockedMessage) {
      super(isLocked, keyId);
      this.direction = direction;
      this.adjacentRoom = adjacentRoom;
      this.lockedMessage = lockedMessage;
      this.isCodeLock = false;
    }
/**
 * Constructor for the Exit class.
 * @param direction Direction you must go from a room to get to this exit
 * @param adjacentRoom The room that the exit connects its room to
 * @param isLocked Whether the exit is locked or not
 * @param keyId The ID of the key used to unlock the exit
 * @param isOpen Whether the exit is open or not
 * @param lockedMessage The message that the exit displays when it is locked
 * @param isCodeLock Whether the lock is a code lock or a normal lock
 */
    public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId, Boolean isOpen, String lockedMessage, boolean isCodeLock) {
      super(isLocked, keyId, isOpen);
      this.direction = direction;
      this.adjacentRoom = adjacentRoom;
      this.lockedMessage = lockedMessage;
      this.isCodeLock = isCodeLock;
    }
  
    public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId, Boolean isOpen) {
      super(isLocked, keyId, isOpen);
      this.direction = direction;
      this.adjacentRoom = adjacentRoom;
      this.lockedMessage = "The door is locked. Will you open it or leave?";
      this.isCodeLock = false;
    }
  
    public Exit(String direction, String adjacentRoom) {
      this.direction = direction;
      this.adjacentRoom = adjacentRoom;
      this.lockedMessage = "The door is locked. Will you open it or leave?";
      this.isCodeLock = false;
    }
  /**
   * 
   * @return direction you must go from a room to get to this exit
   */
    public String getDirection() {
      return direction;
    }
  /**
   * Set the direction you must go from a room to get to this exit
   * @param direction
   */
    public void setDirection(String direction) {
      this.direction = direction;
    }
  /**
   * 
   * @return the room that the exit connects its room to
   */
    public String getAdjacentRoom() {
      return adjacentRoom;
    }
  /**
   * set the room that the exit connects its room to
   * @param adjacentRoom
   */
    public void setAdjacentRoom(String adjacentRoom) {
      this.adjacentRoom = adjacentRoom;
    }
/**
 * 
 * @return the message that the exit displays when it is locked
 */
    public String getLockedMessage() {
      return lockedMessage;
    }
  /**
   * Set the message that the exit displays when it is locked
   * @param lockedMessage
   */
    public void setLockedMessage(String lockedMessage) {
      this.lockedMessage = lockedMessage;
    }
/**
 * 
 * @return whether the lock is a code lock or a normal lock
 */
    public boolean isCodeLock(){
      return isCodeLock;
    }

  }
