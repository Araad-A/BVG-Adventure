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
    private int counter;
    private int score;


    public TestBoss(String name, String introduction, String win, String lose, String loseRoom, String fileName) {
        super(name, introduction, win, lose, loseRoom);
        try {
            initQuestions(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }  
        i=0;
        counter=0;
    }
    /*
    public void Test(String fileName){
        Exam myExam = new Exam();
        myExam.initQuestions(fileName);


        myExam.takeExam();
    }
    */
    
    public int action(String answer){
        if(counter==0){
            questions.get(i).displayQuestion();
            return 0;
        }
        questions.get(i).randomizeAnswer();
        

        boolean isCorrect = questions.get(i).isCorrect(answer);

        if(isCorrect){
            System.out.println("Great job!");
            score++;
        } else {
            System.out.println("Wrong answer!");
        }
        i++;
        if(i==questions.size()){
            System.out.println("Exam is done! Here is your score: " + score + "/" + questions.size());
            if((double)score/questions.size()<0.6){
                return 1;
            }else{
                return 2;
            }
        }
        questions.get(i).displayQuestion();
        System.out.println();
        return 0;

    }

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
