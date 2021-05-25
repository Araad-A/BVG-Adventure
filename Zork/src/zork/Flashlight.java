public class Flashlight extends Item {
  private int battery;
  private boolean isOn;

    public Flashlight(int weight, String name){
        super(weight, name, false);  
        battery = 100;
        isOn = false;
    }

    public void turnOn(){
        isOn = true;
        System.out.println("You shine your flashlight. You can now see items in the room.");
    }

    public int getBattery(){
        return battery;
    }

    public boolean getOn(){
        return isOn;
    }
}
