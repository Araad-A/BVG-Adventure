public class Boss extends Character {
    private String introduction;
    private String win;
    private String lose;
    private int actions;

    public Boss(String name, int health, String introduction, String win, String lose){
        super(name, health);
        this.introduction = introduction;
        this.win = win;
        this.lose = lose;
        this.actions = 0;
    }
}
