public class Flashlight extends Item {
    private Boolean isOn;
  
    public Flashlight(Boolean isOn, String flashlightName, int weight) {
      super(weight, flashlightName, false);
      this.isOn = isOn;
    }
  
    public Boolean getIsOn() {
      return isOn;
    }
  }
