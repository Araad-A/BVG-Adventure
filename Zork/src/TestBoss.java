public class TestBoss extends Boss{

    public TestBoss(String name, int health, String introduction, String win, String lose) {
        super(name, health, introduction, win, lose);
        
    }

    public void Test(String fileName, Scanner in){
        Exam myExam = new Exam();
        myExam.initQuestions(fileName);
        myExam.takeExam(in);
    }
    
}
