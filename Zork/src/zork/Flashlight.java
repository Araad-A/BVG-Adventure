public class Flashlight extends Item {
    private int battery;

    public Flashlight(int weight, String name){
        super(weight, name, false);  
        battery = 100;
    }

    public void turnOn(){
        currentRoom.isDark();
    }

    public int getBattery(){
        return battery;
    }
}
