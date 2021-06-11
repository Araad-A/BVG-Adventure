public class Key extends Item {
    private String keyId;
  /**
   * Constructor for the Key class.
   * @param keyId The ID of the key, more specific than its name
   * @param keyName The name of the key
   * @param weight The weight of the key
   */
    public Key(String keyId, String keyName, int weight) {
      super(weight, keyName);
      this.keyId = keyId;
    }
  /**
   * 
   * @return the ID of the key
   */
    public String getKeyId() {
      return keyId;
    }
  }