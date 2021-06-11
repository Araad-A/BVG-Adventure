package data;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Exam {
    private String name;
    private int numQuestions; //there will be max
    private int numMinutes;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Exam(int numQuestions, int numMinutes){
        name = "Mr. Deslauriers";
        this.numQuestions = numQuestions;
        this.numMinutes = numMinutes;
    }

    public void takeExam(){
        //will use an arraylist to iterate through questions
        //will use a checker to see if answer is correct
        //may need to override the parser???
    }

    public void RandomizeAnswer(Question question){

    }

    
    private void initQuestions(String fileName) throws Exception {
        Path path = Path.of(fileName);
        String jsonString = Files.readString(path);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
    
        JSONArray jsonQuestions = (JSONArray) json.get("questions");
    
        for (Object questionsObj : jsonQuestions) {
          Question question = new Question();
          String ques = (String) ((JSONObject) questionsObj).get("question");
          String answer = (String) ((JSONObject) questionsObj).get("answer");
          String filler1 = (String) ((JSONObject) questionsObj).get("filler1");
          String filler2 = (String) ((JSONObject) questionsObj).get("filler2");
          String filler3 = (String) ((JSONObject) questionsObj).get("filler3");
          question.setQuestion(ques);
          question.setAnswer(answer);
          question.setFiller1(filler1);
          question.setFiller2(filler2);
          question.setFiller3(filler3);
          question.randomizeAnswer();
          questions.add(question);
        }
      }
}
