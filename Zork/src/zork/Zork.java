import java.io.IOException;
import java.util.Scanner;

public class Zork {
    public static void main(String[] args) {
      /*Game game = new Game(); 
      game.play(); */
      Scanner in;
      in = new Scanner(System.in);
      Exam myExam = new Exam();
      try {
        myExam.initQuestions("Zork\\src\\zork\\data\\questions.json");
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      try {
        myExam.takeExam();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /*
  Flashlight class, acid and instrument are all useless
  To do: exit keyId, puzzle + code lock (overloaded constructor) for first boss, all boss fights, character class, ending after beating final boss
  */