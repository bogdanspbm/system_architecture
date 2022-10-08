import commands.*;
import factory.CommandFactory;
import org.junit.Assert;
import org.junit.Test;
import utils.Parser;

import static utils.Stack.getStack;

public class GrepTest extends Assert {

    @Test
    public void testSimpleOnGrep(){
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -w \"французских\" example.txt");
    }



}
