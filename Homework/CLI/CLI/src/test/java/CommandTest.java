

import commands.*;
import org.junit.Assert;
import org.junit.Test;
import utils.Stack;

import java.io.File;
import java.util.Arrays;

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
    public void testLSCommand() {
        CommandLS command = new CommandLS("ls");
        String output = command.buildOutput();
        String[] fileNames = output.split("\n");

        File curDir = new File(System.getProperty("user.dir"));

        File[] files = curDir.listFiles();
        File[] filtered = curDir.listFiles(f -> !f.isHidden());

        assert filtered.length == fileNames.length;

        for (int i = 0; i < filtered.length; i++) {
            assert filtered[i].getName().equals(fileNames[i]);
        }
    }

    @Test
    public void testLSCommandAllInfo() { 
        Stack stack = getStack();
        stack.put("-a");

        CommandLS cmd = new CommandLS("ls");
        String output = cmd.buildOutput();

        File curDir = new File(System.getProperty("user.dir"));

        String[] testNames = output.split("\n");
        String[] trueNames = curDir.list();

        assert trueNames.length == testNames.length;

        for (int i = 0; i < trueNames.length; i++) {
            assert trueNames[i].equals(testNames[i]);
        } 
    }

    @Test
    public void testLSCommandLocalDir() { 
        Stack stack = getStack();
        stack.put("src");

        CommandLS cmd = new CommandLS("ls");
        String output = cmd.buildOutput();

        String[] testNames = output.split("\n");
        String[] trueNames = {"main", "test"};

        assert trueNames.length == testNames.length;

        for (int i = 0; i < trueNames.length; i++) {
            assert trueNames[i].equals(testNames[i]);
        } 
    }

    @Test 
    public void testLSCommandGlobalDir() { 
        Stack stack = getStack();
        stack.put("/");

        CommandLS cmd = new CommandLS("ls");
        String output = cmd.buildOutput();

        File curDir = new File("/"); 

        String[] testNames = output.split("\n");
        File[] trueNames = curDir.listFiles(f -> !f.isHidden());

        assert trueNames.length == testNames.length;

        for (int i = 0; i < trueNames.length; i++) {
            assert trueNames[i].getName().equals(testNames[i]);
        }  
    }

    @Test
    public void testLSCommandDirAllInfo() { 
        Stack stack = getStack();
        stack.put("/");

        CommandLS cmd = new CommandLS("ls");
        String output = cmd.buildOutput();

        File curDir = new File("/"); 

        String[] testNames = output.split("\n");
        String[] trueNames = curDir.list();

        assert trueNames.length == testNames.length;

        for (int i = 0; i < trueNames.length; i++) {
            assert trueNames[i].equals(testNames[i]);
        }
    }

    @Test
    public void testLSCommandFail() { 
        Stack stack = getStack();
        stack.put("unexisting");
        CommandLS cmd = new CommandLS("ls");
        String output = cmd.buildOutput();
        assert output == "Invalid filepath";

        stack.put("-abcd");
        cmd = new CommandLS("ls");
        output = cmd.buildOutput();
        assert output == "Wrong Argument";

        stack.put("this");
        stack.put("three");
        stack.put("words");
        cmd = new CommandLS("ls");
        output = cmd.buildOutput();
        assert output == "Wrong Argument"; 
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

        CommandCat commandC = new CommandCat("cat");
        assert commandC.buildOutput().equals("Wrong argument");
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
        assert commandB.buildOutput().equals("File doesn't exist");

        CommandWC commandC = new CommandWC("cat");
        assert commandC.buildOutput().equals("Wrong argument");
    }

    @Test
    public void testCdCommand() { 
        String startCwd = System.getProperty("user.dir");

        Stack stack = getStack();
        stack.put("src");
        CommandCd cmd = new CommandCd("cd");
        String output = cmd.buildOutput();
        assert output == "";

        String cwd = System.getProperty("user.dir");
        assert cwd == (new File(startCwd + "/src")).getAbsolutePath();

        stack.put("..");
        cmd = new CommandCd("cd");
        output = cmd.buildOutput();
        assert output == "";

        cwd = System.getProperty("user.dir");
        assert cwd == startCwd; 
    }

    @Test
    public void testCdCommandHome() { 
        CommandCd cmd = new CommandCd("cd");
        String output = cmd.buildOutput();
        assert output == "";

        String cwd = System.getProperty("user.dir");
        String home = System.getProperty("user.home");
        assert cwd == home;
    }

    @Test
    public void testCdCommandGlobalDir() { 
        Stack stack = getStack();
        stack.put("/"); 
        CommandCd cmd = new CommandCd("cd");
        String output = cmd.buildOutput();
        assert output == "";

        String cwd = System.getProperty("user.dir");
        assert cwd == "/";
    }

    @Test
    public void testCdCommandFail() { 
        Stack stack = getStack();
        stack.put("too");  
        stack.put("many");
        stack.put("params");
        CommandCd cmd = new CommandCd("cd");
        String output = cmd.buildOutput();
        assert output == "Wrong argument"; 

        stack.put("unexisting");
        cmd = new CommandCd("cd");
        output = cmd.buildOutput();
        assert output == "Directory not found"; 

        stack.put("example.txt");
        cmd = new CommandCd("cd");
        output = cmd.buildOutput();
        assert output == "Not a directory"; 
    }
}
