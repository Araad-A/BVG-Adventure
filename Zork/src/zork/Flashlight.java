public class Flashlight extends Item {
    private int battery;

    public Flashlight(int weight, String name){
        super(weight, name, false);  
        battery = 100;
    }

    public void turnOn(Game game){
        game.getCurrentRoom().setIsDark(false);
        System.out.println("You shine your flashlight. You can now see items in the room.");
    }

    public int getBattery(){
        return battery;
    }
}
