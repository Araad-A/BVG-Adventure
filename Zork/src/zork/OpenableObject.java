public class OpenableObject {
    private Boolean isLocked;
    private String keyId;
    private Boolean isOpen;
 
    public OpenableObject() {
      this.isLocked = false;
      this.keyId = null;
      this.isOpen = false;
    }
   /**
   * The constructor for the OpenableObject class
   * @param isLocked Whether the object is locked or not
   * @param keyId The ID that a key must have to unlock the object
   * @param isOpen Whether the item is open or not
   */
    public OpenableObject(boolean isLocked, String keyId, Boolean isOpen) {
      this.isLocked = isLocked;
      this.keyId = keyId;
      this.isOpen = isOpen;
    }
  
    public OpenableObject(boolean isLocked, String keyId) {
      this.isLocked = isLocked;
      this.keyId = keyId;
      this.isOpen = false;
    }
  /**
   * 
   * @return whether the object is locked or not
   */
    public boolean isLocked() {
      return isLocked;
    }
  /**
   * Set whether the object is locked or not
   * @param isLocked
   */
    public void setLocked(boolean isLocked) {
      this.isLocked = isLocked;
    }
  /**
   * 
   * @return the ID that a key must have to unlock the object
   */
    public String getKeyId() {
      return keyId;
    }
  /**
   * 
   * @return whether the object is open or not
   */
    public boolean isOpen() {
      return isOpen;
    }
  /**
   * set whteher the object is open or not
   * @param isOpen
   */
    public void setOpen(boolean isOpen) {
      this.isOpen = isOpen;
    }
  }
