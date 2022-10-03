import commands.*;
import org.junit.Assert;
import org.junit.Test;
import utils.Stack;

import java.io.File;

import static utils.Stack.getStack;

public class AssignTest extends Assert {

    @Test
    public void testSimpleAssign() {
        CommandAssign commandA = new CommandAssign("a=5");
        commandA.execute();

        getStack().put("$a");

        CommandEcho commandB = new CommandEcho("echo");
        assert commandB.buildOutput().equals("5");
    }
}
