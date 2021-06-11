public class TestBoss extends Boss{

    public TestBoss(String name, int health, String introduction, String win, String lose) {
        super(name, health, introduction, win, lose);
        
    }

    public void Test(String fileName){
        Exam myExam = new Exam();
        myExam.initQuestions(fileName);


        myExam.takeExam();
    }
    
    public String answer(String secondWord){
        return secondWord;
    }
}
