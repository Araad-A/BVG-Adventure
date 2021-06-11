package data;

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
        for(int i = 0; i < displayer.size(); i++){
            int selector = (int) (Math.random() * 4);
            boolean set1 = false;
            boolean set2 = false; 
            boolean set3 = false; 
            boolean set4 = false;

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
