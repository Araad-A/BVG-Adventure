public class Boss extends Character {
    private String introduction;
    private String win;
    private String lose;
    private String loseRoom;
/**
 * Constants for the boss type
 */
    public static final int TEST = 0;
    public static final int FIGHT= 1;
/**
 * Constructor for the Boss class.
 * @param name Name of boss
 * @param introduction Text used to introduce the boss
 * @param win Text used when the boss is defeated
 * @param lose Text used when player loses to the boss
 * @param loseRoom Room player is sent to upon losing
 */
    public Boss(String name, String introduction, String win, String lose, String loseRoom){
        super(name);
        this.introduction = introduction;
        this.win = win;
        this.lose = lose;
        this.loseRoom = loseRoom;
    }
/**
 * 
 * @return the room the player is sent to upon losing
 */
    public String getLoseRoom(){
        return loseRoom;
    }
/**
 * 
 * @return the text used to introduce the boss
 */
    public String getIntroduction(){
        return introduction;
    }
/**
 * 
 * @return the text displayed after defeating the boss
 */
    public String getWin(){
        return win;
    }
/**
 * 
 * @return the text displayed after losing to the boss  
 */
    public String getLose(){
        return lose;
    }

}
