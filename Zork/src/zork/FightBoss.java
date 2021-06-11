public class FightBoss extends Boss{
    private String loseRoom;
    private int move;
    private static final int HIGH_ATTACK = 0;
    private static final int MID_ATTACK = 1;
    private static final int LOW_ATTACK = 2;
    private static final int HIGH_BLOCK = 3;
    private static final int MID_BLOCK = 4;
    private static final int LOW_BLOCK = 5;

    public FightBoss(String name, String introduction, String win, String lose, String loseRoom){
        super(name, introduction, win, lose, loseRoom);
        move = 0;
    }



    public void action(Character player, int playerMove){
        if(move==HIGH_ATTACK){
            System.out.println("Mr. Federico swings his sword downwards.");
            if(playerMove!=HIGH_BLOCK){
                System.out.println("You are hit by the attack and are unable ")
            }
            System.out.println("Mr. Federico raises his sword above his head.");
        }else if(move==MID_ATTACK){
            System.out.println("Mr. Federico pulls his sword back and prepares to thrust his sword.");
        }else if(move==LOW_ATTACK){
            System.out.println("Mr. Federico winds up for a swing of his sword.");
        }else if(move==HIGH_BLOCK){
            System.out.println("Mr. Federico backs up and assumes a more defensive stance.");
        }
        if(move==HIGH_ATTACK){
            System.out.println("Mr. Federico raises his sword above his head.");
        }else if(move==MID_ATTACK){
            System.out.println("Mr. Federico pulls his sword back and prepares to thrust his sword.");
        }else if(move==LOW_ATTACK){
            System.out.println("Mr. Federico winds up for a swing of his sword.");
        }else if(move==HIGH_BLOCK){
            System.out.println("Mr. Federico backs up and assumes a more defensive stance.");
        }
    }
}
