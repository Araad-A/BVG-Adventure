import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestBoss extends Boss{
    private ArrayList<Question> questions = new ArrayList<Question>();
    private int i;
    private int score;

/**
 * Constructor for the TestBoss class
 * @param name Name of the boss
 * @param introduction Text used to introduce the boss
 * @param win Text displayed when player wins
 * @param lose Text displayed when player loses
 * @param loseRoom Room player is sent to upon losing
 * @param fileName Name of JSON file containing questions and answers
 */
    public TestBoss(String name, String introduction, String win, String lose, String loseRoom, String fileName) {
        super(name, introduction, win, lose, loseRoom);
        try {
            initQuestions(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }  
        i=0;
    }
    /**
     * Displays questions, checks if answers are right, keeps track of score and returns result of test
     * @param answer Answer inputted by the player
     * @return 0 = fight continues, 1 = boss wins, 2 = player wins
     */
    public int test(String answer){
        if(i==0){ //the first question is hard-coded in the introduction, it is not randomized, so it is a special case
            if(i==0&&answer.equalsIgnoreCase("b")){
                System.out.println("Great job!");
                score++;
            }else{
                System.out.println("Wrong answer!");
            }
            i++;
            questions.get(i).randomizeAnswer();
            questions.get(i).displayQuestion();
            return 0;
        }
        //checks if answer is correct. If it is, add to score.
        boolean isCorrect = questions.get(i).isCorrect(answer);
        if(isCorrect||(i==0&&answer.equalsIgnoreCase("b"))){
            System.out.println("Great job!");
            score++;
        } else {
            System.out.println("Wrong answer!");
        }
        i++; 

        if(i==questions.size()){ //Checks if test is over, then returns 1 or 2 depending on performance: less than 60% is a loss (1)
            System.out.println("The test is done! Here is your score: " + score + "/" + questions.size());
            if((double)score/questions.size()<0.6){
                score=0;
                i=0;
                return 1;
            }else{
                return 2;
            }
        }
        System.out.println();
        //Randomizes order and prints next set of question and possible answers
        questions.get(i).randomizeAnswer();
        questions.get(i).displayQuestion();
        
        return 0;

    }
/**
 * Initializes questions and possible answers
 * @param fileName The name of the JSON file containing the questions and possible answers
 */
    private void initQuestions(String fileName) throws Exception {
        Path path = Path.of(fileName);
        String jsonString = Files.readString(path);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
    
        JSONArray jsonQuestions = (JSONArray) json.get("questions");
    
        for (Object questionsObj : jsonQuestions) {
          String ques = (String) ((JSONObject) questionsObj).get("question");
          String answer = (String) ((JSONObject) questionsObj).get("answer");
          String filler1 = (String) ((JSONObject) questionsObj).get("filler1");
          String filler2 = (String) ((JSONObject) questionsObj).get("filler2");
          String filler3 = (String) ((JSONObject) questionsObj).get("filler3");
          Question question = null;
          question = new Question(ques, answer, filler1, filler2, filler3);
          questions.add(question);
        }
    }
}
