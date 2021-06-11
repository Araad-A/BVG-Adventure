public class Item {
    private int weight;
    private String name;
    /**
     * Constants to associate item types with ints
     */
    public static final int DEFAULT = 0;
    public static final int KEY = 1;
    public static final int READABLE = 2;
  /**
   * Constructor for the Item class
   * @param weight Weight of the item
   * @param name Name of the item
   */
    public Item(int weight, String name) {
      this.weight = weight;
      this.name = name;
    }
  /**
   * 
   * @return weight of item
   */
    public int getWeight() {
      return weight;
    }
  /**
   * Set weight of item
   * @param weight
   */
    public void setWeight(int weight) {
      this.weight = weight;
    }
  /**
   * 
   * @return name of item
   */
    public String getName() {
      return name;
    }
  /**
   * Set name of item
   * @param name
   */
    public void setName(String name) {
      this.name = name;
    }
  
  }
