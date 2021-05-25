package data;

public class APExam {
    private String name;
    private int numQuestions; //there will be max
    private int numMinutes;

    public APExam(int numQuestions, int numMinutes){
        name = "Mr. Deslauriers";
        this.numQuestions = numQuestions;
        this.numMinutes = numMinutes;
    }

    public void greet(){
        System.out.println("Ah, hello! I am " + name);
        System.out.println("Trapped? Well, in order to get to the second floor, you must complete this AP Computer Science Exam");
    }

    public void takeAPExam(){
        //will use an arraylist to iterate through questions
        //will use a checker to see if answer is correct
        //may need to override the parser???
    }
}
