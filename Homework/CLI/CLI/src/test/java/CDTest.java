import commands.*;
import factory.CommandFactory;
import org.junit.Assert;
import org.junit.Test;
import utils.Parser;
import global.SharedVariables;
import utils.VariableStorage;
import global.exceptions.FileNotFoundException;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;

import static utils.Stack.getStack;
import static utils.Parser.parse;

public class CDTest extends Assert {

    @Test
    public void testCDWithoutArguments() {
        VariableStorage storage = VariableStorage.getStorage();
        storage.put("HOME", "/home/gentoo");
        SharedVariables.setCurPath("/home/gentoo/foo/bar");

        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("cd");
        getStack().putArray(words);

        Command command = factory.createCommand(getStack().get());
        command.execute();

        assert command.buildOutput().equals("");
        assert SharedVariables.getCurPath().equals(storage.get("HOME"));

        resetState();
    }

    @Test
    public void testCDWithValidPathArgument() throws IOException {
      VariableStorage storage = VariableStorage.getStorage();
      CommandFactory factory = new CommandFactory();

      File file = Files.createTempDirectory("java-CDTest").toFile();
      file.deleteOnExit();

      String initialCurrentPath = SharedVariables.getCurPath();
      String[] words = Parser.parse("cd " + file.getAbsolutePath());
      getStack().putArray(words);

      Command command = factory.createCommand(getStack().get());
      command.execute();

      assert SharedVariables.getCurPath().equals(file.getAbsolutePath());

      resetState();
    }

    @Test(expected = FileNotFoundException.class)
    public void testCDWithNonexistentPathArgument() {
      VariableStorage storage = VariableStorage.getStorage();
      CommandFactory factory = new CommandFactory();

      String initialCurrentPath = SharedVariables.getCurPath();
      String[] words = Parser.parse("cd foo");
      getStack().putArray(words);

      Command command = factory.createCommand(getStack().get());
      command.execute();

      resetState();
    }

    private void resetState() {
        SharedVariables.setCurPath("");
    }
}
