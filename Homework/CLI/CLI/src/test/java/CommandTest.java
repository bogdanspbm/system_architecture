

import commands.*;
import org.junit.Assert;
import org.junit.Test;
import utils.Stack;

import java.io.File;

import static utils.Stack.getStack;


public class CommandTest extends Assert {

    @Test
    public void testEchoCommand() {
        Stack stack = getStack();

        stack.put("word");
        CommandEcho commandA = new CommandEcho("echo");
        assert commandA.buildOutput().equals("word");

        stack.put("hello");
        stack.put("world");
        CommandEcho commandB = new CommandEcho("echo");
        assert commandB.buildOutput().equals("hello world");

        stack.put("this");
        stack.put("is");
        stack.put("a");
        stack.put("big");
        stack.put("message");
        CommandEcho commandC = new CommandEcho("echo");
        assert commandC.buildOutput().equals("this is a big message");
    }

    @Test
    public void testOutCommand() {
        Stack stack = getStack();

        stack.put("main.py");
        CommandOut commandA = new CommandOut("python");
        assert commandA.buildOutput().equals("Hello world!\n");

        stack.put("test.py");
        CommandOut commandB = new CommandOut("python");
        assert commandB.buildOutput().equals("");
    }

    @Test
    public void testPWDCommand() {
        CommandPWD commandA = new CommandPWD("pwd");
        assert commandA.buildOutput().equals(System.getProperty("user.dir"));
    }

    @Test
    public void testCatCommand() {
        Stack stack = getStack();

        stack.put("example.txt");

        CommandCat commandA = new CommandCat("cat");

        String[] lines = commandA.buildOutput().split("\n");

        assert lines[0].equals("попробуй этих мягких французских булок");
        assert lines[1].equals("да выпей чаю");

        stack.put("bad.txt");
        CommandCat commandB = new CommandCat("cat");
        assert commandB.buildOutput().equals("File doesn't exist");
    }

    @Test
    public void testWCCommand() {
        Stack stack = getStack();

        stack.put("example.txt");

        CommandWC commandA = new CommandWC("wc");

        String[] lines = commandA.buildOutput().split(" ");

        assert lines[0].equals("2");
        assert lines[1].equals("8");
        //assert lines[2].equals("96");

        stack.put("bad.txt");
        CommandWC commandB = new CommandWC("wc");
        assert commandB.buildOutput().equals("1 1 0 ");

        CommandWC commandC = new CommandWC("cat");
        assert commandC.buildOutput().equals("Wrong argument");
    }
}
