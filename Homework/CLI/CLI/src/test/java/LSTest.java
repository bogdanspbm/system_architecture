import commands.*;
import global.SharedVariables;
import utils.VariableStorage;
import factory.CommandFactory;
import utils.Parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;

import static utils.Stack.getStack;
import static utils.Parser.parse;

public class LSTest extends Assert {
    
    File fileA, fileB, fileC, fileD;
    File subfolder;
    File fileX, fileY, fileZ;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        fileA = folder.newFile("fileA.txt");
        fileB = folder.newFile("img.png");
        fileC = folder.newFile("main.py");
        fileD = folder.newFile("source.java");
        subfolder = folder.newFolder("subfolder");
        fileX = folder.newFile("subfolder/magical-tree.jpg");
        fileY = folder.newFile("subfolder/song.wav");
        fileZ = folder.newFile("subfolder/map.pdf");
    }

    @Test
    public void testOnLsWithDefaultParameter() {
        CommandLS commandLS = new CommandLS("ls");
        String output = commandLS.buildOutput();
        List<String> fileNames = new LinkedList<String>(Arrays.asList(output.split(" ")));
        fileNames.remove(fileNames.size() - 1);

        File curDir = new File(SharedVariables.getCurPath());

        File[] files = curDir.listFiles();

        assert files.length == fileNames.size();

        for (int i = 0; i < files.length; i++) {
            assert fileNames.contains(files[i].getName());
            fileNames.remove(files[i].getName());
        }
    }

    @Test
    public void testOnLsWithAbsentPath() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("ls __nonsense_avs");
        getStack().putArray(words);

        Command commandLS = factory.createCommand(getStack().get());

        assert (commandLS.buildOutput()).equals("ls: cannot access '__nonsense_avs': No such file or directory\n");
    }

    @Test
    public void testOnLsWithOneFolderParam() {
        String tempDirPath = Paths.get(System.getProperty("java.io.tmpdir"),
                                       folder.getRoot().getName()).toString();

        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse(String.format("ls %s", tempDirPath));
        getStack().putArray(words);

        Command commandLS = factory.createCommand(getStack().get());
        
        String expectedOutput = "fileA.txt img.png main.py source.java subfolder \n";
        String actualOutput = commandLS.buildOutput();
        assert equalOutputs(expectedOutput, actualOutput);

    }

    private boolean equalOutputs(String expectedOutput, String actualOutput)
    {
        assert actualOutput.length() == expectedOutput.length();
        String[] expectedLines = expectedOutput.split("\n"); 
        String[] actualLines = actualOutput.split("\n");
        for (int i = 0; i < expectedLines.length; ++i)
        {
            if (expectedLines[i] == actualLines[i])
                continue;
            if (expectedLines[i].length() != actualLines[i].length())
                return false;
            
            String[] expectedWordsOnLine = expectedLines[i].split(" ");
            String[] actualWordsOnLine = actualLines[i].split(" ");
            Arrays.sort(expectedWordsOnLine);
            Arrays.sort(actualWordsOnLine);
            if (!Arrays.equals(expectedWordsOnLine, actualWordsOnLine))
                return false;
        }
        return true;
    }
}
