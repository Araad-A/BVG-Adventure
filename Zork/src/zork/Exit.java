/**
 * Exit
 */
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
  
    public String getDirection() {
      return direction;
    }
  
    public void setDirection(String direction) {
      this.direction = direction;
    }
  
    public String getAdjacentRoom() {
      return adjacentRoom;
    }
  
    public void setAdjacentRoom(String adjacentRoom) {
      this.adjacentRoom = adjacentRoom;
    }

    public String getLockedMessage() {
      return lockedMessage;
    }
  
    public void setLockedMessage(String lockedMessage) {
      this.lockedMessage = lockedMessage;
    }

    public boolean isCodeLock(){
      return isCodeLock;
    }

  }
