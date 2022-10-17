import commands.*;
import org.junit.Assert;
import org.junit.Test;
import utils.Parser;


import static utils.Stack.getStack;

public class AssignTest extends Assert {

    @Test
    public void testSimpleAssignNumber() {

        CommandAssign commandSimpleNumber = new CommandAssign("a=5");
        commandSimpleNumber.execute();

        getStack().put("$a");

        CommandEcho commandValidSimpleNumber = new CommandEcho("echo");
        assert commandValidSimpleNumber.buildOutput().equals("5");


        CommandAssign commandSimpleLetter = new CommandAssign("c=b");
        commandSimpleLetter.execute();

        getStack().put("$c");

        CommandEcho commandValidSimpleLetter = new CommandEcho("echo");
        assert commandValidSimpleLetter.buildOutput().equals("b");


        CommandAssign commandLetterInComa = new CommandAssign(Parser.parse("k=\"b\"")[0]);
        commandLetterInComa.execute();

        getStack().put("$k");

        CommandEcho commandValidLetterInComa = new CommandEcho("echo");
        assert commandValidLetterInComa.buildOutput().equals("b");



        CommandAssign commandLetterInAloneComa = new CommandAssign(Parser.parse("p='b'")[0]);
        commandLetterInAloneComa.execute();

        getStack().put("$p");

        CommandEcho commandValidLetterInAloneComa = new CommandEcho("echo");
        assert commandValidLetterInAloneComa.buildOutput().equals("b");


        CommandAssign commandCharInPipe = new CommandAssign("r=|6|");
        commandCharInPipe.execute();

        getStack().put("$r");

        CommandEcho commandValidCharInPipe = new CommandEcho("echo");
        assert commandValidCharInPipe.buildOutput().equals("|6|");


        CommandAssign commandEqualsUnderComas = new CommandAssign("t=\"=6\"");
        commandEqualsUnderComas.execute();

        getStack().put("$t");

        CommandEcho commandValidEqualsUnderComas = new CommandEcho("echo");
        assert commandValidEqualsUnderComas.buildOutput().equals("\"=6\"");


        CommandAssign commandSpace = new CommandAssign("l=");
        commandSpace.execute();

        getStack().put("$l");

        CommandEcho commandValidSpace = new CommandEcho("echo");
        assert commandValidSpace.buildOutput().equals("");


        CommandAssign commandNothingInitials = new CommandAssign("=100");
        commandNothingInitials.execute();

        getStack().put("$");

        CommandEcho commandValidNothingInitials = new CommandEcho("echo");
        assert commandValidNothingInitials.buildOutput().equals("$");

    }

    @Test
    public void testSimpleModulation() {

        CommandAssign commandSimple = new CommandAssign("a=5");
        commandSimple.execute();

        CommandAssign commandSimpleEquilsVariables = new CommandAssign("b=a");
        commandSimpleEquilsVariables.execute();

        getStack().put("$b");

        CommandEcho commandValidSimpleNumber = new CommandEcho("echo");
        assert commandValidSimpleNumber.buildOutput().equals("a");

    }

}
