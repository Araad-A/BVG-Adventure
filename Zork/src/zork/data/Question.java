package data;

public class Question {
    private String question;
    private String answer;
    private String filler1;
    private String filler2;
    private String filler3;

    public Question(String question, String answer, String filler1, String filler2, String filler3){
        this.question = question;
        this.answer = answer;
        this.filler1 = filler1;
        this.filler2 = filler2;
        this.filler3 = filler3;
    }

    public void askQuestion(){
        System.out.println(question);
    }

}
