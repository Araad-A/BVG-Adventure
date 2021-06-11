package data;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;

public class Exam {
    private ArrayList<Question> questions = new ArrayList<Question>();

    public void takeExam(Scanner in){
        String inputLine = "";
        int score = 0;
        int numQuestions = 0;
        System.out.println("Please answer with either \"A\" \"B\" \"C\" or \"D\"");

        for(int i = 0; i < questions.size(); i++){
            questions.get(i).displayQuestion();
            boolean isValid = false;

            while(!isValid){
                inputLine = in.nextLine();
                isValid = Question.isValidAnswer(inputLine);

                if(!isValid)
                    System.out.println("Not a valid answer");
            }

            boolean isCorrect = questions.get(i).isCorrect(inputLine);

            if(isCorrect){
                System.out.println("Great job! Next Question!");
                score++;
            } else {
                System.out.println("Wrong answer! Next Question!");
            }
            System.out.println();
            numQuestions++;
        }
        System.out.println("Exam is done! Here is your score: " + score + "/" + numQuestions);
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
