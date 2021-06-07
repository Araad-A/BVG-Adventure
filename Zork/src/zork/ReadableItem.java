public class ReadableItem extends Item {
    
    private String contents;

    public ReadableItem(int weight, String name, String contents){
        super(weight, name, false);  
        this.contents = contents;
    }

    public void read(){
        System.out.println(contents);
    }
}
