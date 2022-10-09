import commands.*;
import factory.CommandFactory;
import org.junit.Assert;
import org.junit.Test;
import utils.Parser;

import static utils.Stack.getStack;
import static utils.Parser.parse;

public class GrepTest extends Assert {

    @Test
    public void testSimpleOnGrepOnOneWordInRowWithFlagWAndWithoutIt() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -w \"точка\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("точка\n");

        String[] wordsWithoutW = Parser.parse("grep  \"точка\" exampleSecond.txt");
        getStack().putArray(wordsWithoutW);

        Command cmdGreepWithoutW = factory.createCommand(getStack().get());

        assert (cmdGreepWithoutW.buildOutput()).equals("точка\n");

        String[] wordsNotValidWithW = Parser.parse("grep -w \"точ\" exampleSecond.txt");
        getStack().putArray(wordsNotValidWithW);

        Command cmdGreepNotValidOnW = factory.createCommand(getStack().get());

        assert (cmdGreepNotValidOnW.buildOutput()).equals("");

    }

    @Test
    public void testSimpleOnGrepOnWordsInRow() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -w \"новое\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("попробуй новое предложение\n");

    }

    @Test
    public void testOnGrepOnWordsInRowWithPipe() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -w \"родителями\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("приходи с друзьями | родителями\n");

    }

    @Test
    public void testOnGrepOnFakeWord() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -w \"класс\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("");

    }

    @Test
    public void testOnGrepOnNotValidWord() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -w \"родителями$\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("");

        String[] wordsWithDollar = Parser.parse("grep -w \"^родителями\" exampleSecond.txt");
        getStack().putArray(wordsWithDollar);

        Command cmdGreepWithDollar = factory.createCommand(getStack().get());

        assert (cmdGreepWithDollar.buildOutput()).equals("");

    }

    @Test
    public void testSimpleOnThePartOfTheWord() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep  \"франц\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("попробуй этих мягких французских булок\n");

    }

    @Test
    public void testSimpleOnSearcingWordWithTwoInFile() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep  \"купи\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("купи \"квас\"\nкупи 2 квас\n");

    }

    @Test
    public void testOnGrepWitFlagI() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -i \"ривер\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("крутой Ривер\n");

    }

    @Test
    public void testOnGrepWithFlagA() {
        CommandFactory factory = new CommandFactory();

        String[] words = Parser.parse("grep -A0 \"чаю\" exampleSecond.txt");
        getStack().putArray(words);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("да выпей чаю\n");

        String[] wordsWithFewRows = Parser.parse("grep -A3 \"чаю\" exampleSecond.txt");
        getStack().putArray(wordsWithFewRows);

        Command cmdGreepWithFewRows = factory.createCommand(getStack().get());

        assert (cmdGreepWithFewRows.buildOutput()).equals("приходи с друзьями | родителями\nкупи 2 квас\nточка\n");

        String[] wordsWithFewRowsAfterLastRow = Parser.parse("grep -A10 \"крутой\" exampleSecond.txt");
        getStack().putArray(wordsWithFewRowsAfterLastRow);

        Command cmdGreepWithFewRowsAfterLastRow = factory.createCommand(getStack().get());

        assert (cmdGreepWithFewRowsAfterLastRow.buildOutput()).equals("крутой Ривер\n");

        String[] wordsWithFewRowsOnNotValidWord = Parser.parse("grep -A5 \"учитель\" exampleSecond.txt");
        getStack().putArray(wordsWithFewRowsOnNotValidWord);

        Command cmdGreepWithFewRowsOnNotValidWord = factory.createCommand(getStack().get());

        assert (cmdGreepWithFewRowsOnNotValidWord.buildOutput()).equals("");

    }

    @Test
    public void testOnGrepWithMixeFlags() {
        CommandFactory factory = new CommandFactory();

        String[] wordsFlagsAIW = Parser.parse("grep  -A3 -i -w  \"квас\"  exampleSecond.txt");
        getStack().putArray(wordsFlagsAIW);

        Command cmdGreep = factory.createCommand(getStack().get());

        assert (cmdGreep.buildOutput()).equals("купи \"квас\"\nприходи с друзьями | родителями\nкупи 2 квас\n");

        String[] wordsFlagsAIWSmallRegister = Parser.parse("grep  -A2 -i -w  \"Новое\"  exampleSecond.txt");
        getStack().putArray(wordsFlagsAIWSmallRegister);

        Command cmdGreepFlagsAIWSmallRegister = factory.createCommand(getStack().get());

        assert (cmdGreepFlagsAIWSmallRegister.buildOutput()).equals("попробуй новое предложение\nкупи \"квас\"\nприходи с друзьями | родителями\n");

        String[] wordsFlagsIWWithDuplicateInText = Parser.parse("grep  -i  -w  \"купи\"  Untitled1.txt");
        getStack().putArray(wordsFlagsIWWithDuplicateInText);

        Command cmdGreepFlagsIWWithDuplicateInText = factory.createCommand(getStack().get());

        assert (cmdGreepFlagsIWWithDuplicateInText.buildOutput()).equals("купи \"квас\"купи 2 квас\n");

        String[] wordsFlagsAWConcatenation = Parser.parse("grep  -A3w  \"этих\"  exampleSecond.txt");
        getStack().putArray(wordsFlagsAWConcatenation);

        Command cmdGreepFlagsAWConcatenation = factory.createCommand(getStack().get());

        assert (cmdGreepFlagsAWConcatenation.buildOutput()).equals("");

    }

    @Test
    public void testSimpleOnParserWithGrep(){
        String[] parserSimpleTestOneWord =  parse("grep \"точка\" exampleSecond.txt");
        String[] parserValidSimpleTestOneWord = new String[] {"grep","точка","exampleSecond.txt"};
        assertArrayEquals(parserSimpleTestOneWord,parserValidSimpleTestOneWord);

    }
}
