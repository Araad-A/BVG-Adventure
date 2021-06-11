public class ReadableItem extends Item {
    
    private String contents;
/**
 * Constructor for the ReadableItem class
 * @param weight Weight of the item
 * @param name Name of the item
 * @param contents What is written on/in the item
 */
    public ReadableItem(int weight, String name, String contents){
        super(weight, name);  
        this.contents = contents;
    }
/**
 * Prints the text of this item
 */
    public void read(){
        System.out.println(contents);
    }
}
