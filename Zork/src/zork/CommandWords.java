public class CommandWords {
    // a constant array that holds all valid command words
    private static final String validCommands[] = { "go", "quit", "help", "eat", "sleep", "use", "leave", "inventory", "take", "read", "enter", "answer", "attack", "block", "workout", "n", "e", "s", "w", "ne", "se", "sw", "nw", "u", "d", "north", "east", "south", "west", "northeast", "southeast", "southwest", "northwest", "up", "down" };
  
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
      // nothing to do at the moment...
    }
  
    /**
     * Check whether a given String is a valid command word. Return true if it is,
     * false if it isn't.
     **/
    public boolean isCommand(String aString) {
      for (String c : validCommands) {
        if (c.equals(aString))
          return true;
      }
      // if we get here, the string was not found in the commands
      return false;
    }
  
    /*
     * Print all valid commands to System.out.
     */
    public void showAll() {
      for (String c : validCommands) {
        System.out.print(c + "  ");
      }
      System.out.println();
    }
  }
