

import java.util.ArrayList;

public class Question {
    private String question;
    private String answer;
    private String filler1;
    private String filler2;
    private String filler3;
    private ArrayList<String> displayer;
    private int num;

    public Question(){
        setQuestion("default");
        setAnswer("default");
        setFiller1("default");
        setFiller2("default");
        setFiller3("default");
        displayer = new ArrayList<String>(4);
    }

    public void randomizeAnswer(){
        boolean set1 = false;
        boolean set2 = false; 
        boolean set3 = false; 
        boolean set4 = false;

        while((!set1 || !set2) || (!set3 || !set4)){
            int selector = (int) (Math.random() * 4);

            if(selector == 0 && !set1){
                displayer.add(answer);
                set1 = true;
            } else if(selector == 1 && !set2){
                displayer.add(filler1);
                set2 = true;
            } else if(selector == 2 && !set3){
                displayer.add(filler2);
                set3 = true;
            } else if(selector == 3 && !set4){
                displayer.add(filler3);
                set4 = true;
            }
                
        }
    }

    public void displayQuestion(){
        System.out.println(question);
        System.out.println("A)" + displayer.get(0));
        System.out.println("B)" + displayer.get(1));
        System.out.println("C)" + displayer.get(2));
        System.out.println("D)" + displayer.get(3));
    }

    public boolean isCorrect(Command command){
        String letter = command.getSecondWord();
        
        if(letter.equalsIgnoreCase("A")){
            return displayer.get(0).equals(answer);
        } else if(letter.equalsIgnoreCase("B")){
            return displayer.get(1).equals(answer);
        } else if(letter.equalsIgnoreCase("C")){
            return displayer.get(2).equals(answer);
        } else if(letter.equalsIgnoreCase("D")){
            return displayer.get(3).equals(answer);
        }
        return false;
    }

    public static boolean isValidAnswer(Command command){
        String letter = command.getSecondWord();
        if(letter.equalsIgnoreCase("A") || letter.equalsIgnoreCase("B") || letter.equalsIgnoreCase("C") || letter.equalsIgnoreCase("D"))
            return true;
            
        return false;
    }

    public String getFiller3() {
        return filler3;
    }

    public void setFiller3(String filler3) {
        this.filler3 = filler3;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
