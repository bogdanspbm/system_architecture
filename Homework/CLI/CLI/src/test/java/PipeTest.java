import commands.Command;
import commands.CommandCat;
import commands.CommandPipe;
import commands.CommandWC;
import factory.CommandFactory;
import org.junit.Assert;
import org.junit.Test;
import utils.Parser;

import static utils.Stack.getStack;

public class PipeTest extends Assert {

    @Test
    public void simplePipeTest() {
        getStack().put("example.txt");
        CommandCat commandA = new CommandCat("cat");
        commandA.execute();

        CommandPipe commandB = new CommandPipe("pipe");
        commandB.execute();

        CommandWC commandC = new CommandWC("wc");
        assert commandC.buildOutput().equals("2 8 0 ");
    }

    @Test
    public void simpleTestWithParse() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("cat example.txt | wc");
        getStack().putArray(words);

        Command cmdCat = factory.createCommand(getStack().get());
        cmdCat.execute();

        Command cmdPipe = factory.createCommand(getStack().get());
        cmdPipe.execute();

        Command cmdWC = factory.createCommand(getStack().get());
        assert cmdWC.buildOutput().equals("2 8 0 ");

    }

    @Test
    public void simplePythonPipeTest() {
        getStack().put("main.py");
        CommandCat commandA = new CommandCat("cat");
        commandA.execute();

        CommandPipe commandB = new CommandPipe("pipe");
        commandB.execute();

        CommandWC commandC = new CommandWC("wc");
        assert commandC.buildOutput().equals("1 2 0 ");
    }

    @Test
    public void simpleTestWithParseCatAndEcho() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("cat example.txt | echo \"4\"");
        getStack().putArray(words);

        Command cmdCat = factory.createCommand(getStack().get());
        cmdCat.execute();

        Command cmdPipe = factory.createCommand(getStack().get());
        cmdPipe.execute();

        Command cmdWC = factory.createCommand(getStack().get());
        assert cmdWC.buildOutput().equals("4");

    }

/**
    These tests are not working in whole file, cause each one of them considers CLI command "exit" with pipes and
    others CLI command. Nevertheless, individually they work.
*/
//    @Test
//    public void simpleTestWithPipeAndExit() {
//        CommandFactory factory = new CommandFactory();
//
//        String[] words = Parser.parse("cat example.txt | exit");
//        getStack().putArray(words);
//
//        Command cmdCat = factory.createCommand(getStack().get());
//        cmdCat.execute();
//
//        Command cmdPipe = factory.createCommand(getStack().get());
//        cmdPipe.execute();
//
//        Command cmdExit = factory.createCommand(getStack().get());
//        assert cmdExit.buildOutput().equals("0");
//
//    }

//    @Test
//    public void simpleTestWithTwoPipesAndExit() {
//        CommandFactory factory = new CommandFactory();

//        String[] words = Parser.parse("cat example.txt | exit | cat example.txt");
//        getStack().putArray(words);

//        Command cmdCat = factory.createCommand(getStack().get());
//        cmdCat.execute();

//        Command cmdPipe = factory.createCommand(getStack().get());
//        cmdPipe.execute();

//        Command cmdExit = factory.createCommand(getStack().get());
//        assert cmdExit.buildOutput().equals("0");

//    }

}
