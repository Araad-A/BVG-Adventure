public class FightBoss extends Boss{
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



    public int fight(Character player, int playerMove){
        boolean playerHit = false;
        boolean playerBlock = false;
        boolean enemyHit = false;
        boolean enemyBlock = false;
        if(move==HIGH_ATTACK){
            System.out.println("Mr. Federico swings his sword downwards.");
            if(playerMove!=HIGH_BLOCK)
                playerHit=true;
            else
                playerBlock=true;
        }else if(move==MID_ATTACK){
            System.out.println("Mr. Federico thrusts his sword at your torso.");
            if(playerMove!=MID_BLOCK)
                playerHit=true;
            else
                playerBlock=true;
        }else if(move==LOW_ATTACK){
            System.out.println("Mr. Federico slashes at your leg.");
            if(playerMove!=LOW_BLOCK)
                playerHit=true;
            else
                playerBlock=true;
        }else if(move==HIGH_BLOCK){
            System.out.println("Mr. Federico brings his sword upwards, expecting a strike to the head.");
            if(playerMove==HIGH_ATTACK)
                enemyBlock=true;
            else if(playerMove==LOW_ATTACK||playerMove==MID_ATTACK)
                enemyHit=true;
        }else if(move==MID_BLOCK){
            System.out.println("Mr. Federico parries near the middle of his body.");
            if(playerMove==MID_ATTACK)
                enemyBlock=true;
            else if(playerMove==HIGH_ATTACK||playerMove==LOW_ATTACK)
                enemyHit=true;
        }else if(move==LOW_BLOCK){
            System.out.println("Mr. Federico holds his sword pointed downwards to block low attacks.");
            if(playerMove==LOW_ATTACK)
                enemyBlock=true;
            else if(playerMove==HIGH_ATTACK||playerMove==MID_ATTACK)
                enemyHit=true;
        }
        if(playerHit){
            System.out.println("You are hit by the attack and are unable to move.");
            player.setHealth(player.getHealth()-(int)(Math.random()*10+20));
        }else if(playerBlock){
            System.out.println("You block the attack.");
        }else if(enemyHit){
            System.out.println("Mr. Federico is hit by the attack.");
            setHealth(getHealth()-(int)(Math.random()*10+20));
        }else if(enemyBlock){
            System.out.println("Mr. Federico blocks the attack.");
        }

        System.out.println("Your health: " + player.getHealth());
        System.out.println("Mr. Federico's health: " + getHealth());
        if(player.getHealth()<=0){
            return 1;
        }else if(getHealth()<=0){
            return 2;
        }
        move=(int)(Math.random()*6);

        if(move==HIGH_ATTACK){
            System.out.println("Mr. Federico raises his sword above his head.");
        }else if(move==MID_ATTACK){
            System.out.println("Mr. Federico pulls his sword back and prepares to thrust his sword.");
        }else if(move==LOW_ATTACK){
            System.out.println("Mr. Federico winds up for a swing of his sword.");
        }else if(move==HIGH_BLOCK||move==MID_BLOCK||move==LOW_BLOCK){
            System.out.println("Mr. Federico backs up and assumes a more defensive stance.");
        }
        return 0;
    }
}
