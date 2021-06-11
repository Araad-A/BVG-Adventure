public class Boss extends Character {
    private String introduction;
    private String win;
    private String lose;
    private String loseRoom;
    private int actions;
    public static final int TEST = 0;
    public static final int FIGHT= 1;

    public Boss(String name, String introduction, String win, String lose, String loseRoom){
        super(name);
        this.introduction = introduction;
        this.win = win;
        this.lose = lose;
        this.actions = 0;
        this.loseRoom = loseRoom;
    }

    public String getLoseRoom(){
        return loseRoom;
    }

    public String getIntroduction(){
        return introduction;
    }

    public String getWin(){
        return win;
    }

    public String getLose(){
        return lose;
    }

    public int action(int x){
        return 0;
    }
}
