

import java.util.ArrayList;

public class Question {
    private String question;
    private String answer;
    private String filler1;
    private String filler2;
    private String filler3;
    private ArrayList<String> displayer;
/**
 * Constructor for the Question class.
 * @param question The string containing the question being asked
 * @param answer The string containing the correct answer
 * @param filler1 A string containing a wrong answer
 * @param filler2 A string containing a wrong answer
 * @param filler3 A string containing a wrong answer
 */
    public Question(String question, String answer, String filler1, String filler2, String filler3){
        this.question = question;
        this.answer = answer;
        this.filler1 = filler1;
        this.filler2 = filler2;
        this.filler3 = filler3;
        displayer = new ArrayList<String>(4);
    }
/**
 * Fill the displayer ArrayList with possible answers in a random order
 */
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
/**
 * Print the question and possible answers
 */
    public void displayQuestion(){
        System.out.println(question);
        System.out.println("A)" + displayer.get(0));
        System.out.println("B)" + displayer.get(1));
        System.out.println("C)" + displayer.get(2));
        System.out.println("D)" + displayer.get(3));
    }
/**
 * Check if a letter that is inputted matches the correct answer
 * @param letter The answer being passed in
 * @return Whether the answer is right or not (letter matches correct letter)
 */
    public boolean isCorrect(String letter){
        
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
/**
 * 
 * @return The string of the third wrong answer
 */
    public String getFiller3() {
        return filler3;
    }
/**
 * Set the string of the third wrong answer
 * @param filler3
 */
    public void setFiller3(String filler3) {
        this.filler3 = filler3;
    }
/**
 * 
 * @return The string of the second wrong answer
 */
    public String getFiller2() {
        return filler2;
    }
/**
 * Set the string of the second wrong answer
 * @param filler2
 */
    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }
/**
 * 
 * @return the string of the first wrong answer
 */
    public String getFiller1() {
        return filler1;
    }
/**
 * Set the string of the first wrong answer
 * @param filler1
 */
    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }
/**
 * 
 * @return the string of the correct answer
 */
    public String getAnswer() {
        return answer;
    }
/**
 * Set the string of the correct answer
 * @param answer
 */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
/**
 * 
 * @return the string of the question
 */
    public String getQuestion() {
        return question;
    }
/**
 * Set the string of the question
 * @param question
 */
    public void setQuestion(String question) {
        this.question = question;
    }
}
