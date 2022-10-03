import commands.*;
import org.junit.Assert;
import org.junit.Test;


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


        CommandAssign commandLetterInComa = new CommandAssign("k=\"b\"");
        commandLetterInComa.execute();

        getStack().put("$k");

        CommandEcho commandValidLetterInComa = new CommandEcho("echo");
        assert commandValidLetterInComa.buildOutput().equals("\"b\"");

        //!!!!!!!!!!!! выводит без одинарных кавычек букву b
        CommandAssign commandLetterInAloneComa = new CommandAssign("p='b'");
        commandLetterInAloneComa.execute();

        getStack().put("$p");

        CommandEcho commandValidLetterInAloneComa = new CommandEcho("echo");
        assert commandValidLetterInAloneComa.buildOutput().equals("'b'");

        //!!!!!!!!!!!!!!!! вообще ничего не выводит у тебя
        CommandAssign commandCharInPipe = new CommandAssign("r=|6|");
        commandCharInPipe.execute();

        getStack().put("$r");

        CommandEcho commandValidCharInPipe = new CommandEcho("echo");
        assert commandValidCharInPipe.buildOutput().equals("|6|");

        //!!!!!!!!!!!!!!! вместо =6 тупо присваивает шестерку переменной)
        CommandAssign commandEqualsUnderComas = new CommandAssign("t=\"=6\"");
        commandEqualsUnderComas.execute();

        getStack().put("$t");

        CommandEcho commandValidEqualsUnderComas = new CommandEcho("echo");
        assert commandValidEqualsUnderComas.buildOutput().equals("\"6\"");


        CommandAssign commandSpace = new CommandAssign("l=");
        commandSpace.execute();

        getStack().put("$l");

        CommandEcho commandValidSpace = new CommandEcho("echo");
        assert commandValidSpace.buildOutput().equals("");


        CommandAssign commandNothingInitials = new CommandAssign("=100");
        commandNothingInitials.execute();

        getStack().put("$");

        CommandEcho commandValidNothingInitials = new CommandEcho("echo");
        assert commandValidNothingInitials.buildOutput().equals("100");

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
