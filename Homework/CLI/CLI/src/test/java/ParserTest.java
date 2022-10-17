

import org.junit.Assert;
import org.junit.Test;

import static utils.Parser.parse;



public class ParserTest extends Assert {

    @Test
    public void testParserPipe() {

        String[] parserOnePipe =  parse(" echo \"lk|\"");
        assertEquals(parserOnePipe[0],"echo");
        assertEquals(parserOnePipe[1],"lk|");

        String[] parserTwoPipes =  parse(" echo \"|qwer|\"");
        String[] parserValidTwoPipe = new String[] {"echo","|qwer|"};
        assertArrayEquals(parserTwoPipes,parserValidTwoPipe);

        String[] parserOnePipeSpace =  parse(" echo \" | \"");
        String[] parserValidOnePipeSpace = new String[] {"echo"," | "};
        assertArrayEquals(parserOnePipeSpace,parserValidOnePipeSpace);

        String[] parserOnePipeWithWords =  parse(" echo \" gf|fg \"");
        String[] parserValidOnePipeWithWords = new String[] {"echo"," gf|fg "};
        assertArrayEquals(parserOnePipeWithWords,parserValidOnePipeWithWords);
    }

    @Test
    public void testParserQuotes() {

        String[] parserOneQuotesWithoutSpace =  parse(" echo \"\"\"");
        String[] parserValidOneQuotesWithoutSpace = new String[] {"echo",""};
        assertArrayEquals(parserValidOneQuotesWithoutSpace,parserOneQuotesWithoutSpace);

        String[] parserOneQuotesWithSpace =  parse(" echo \" \" \"");
        String[] parserValidOneQuotesWithSpace = new String[] {"echo"," ",""};
        assertArrayEquals(parserValidOneQuotesWithSpace,parserOneQuotesWithSpace);


        String[] parserTwoQuotesWithoutSpace =  parse(" echo \"\"\"\"");
        String[] parserValidTwoQuotesWithoutSpace = new String[] {"echo", ""};
        assertArrayEquals(parserValidTwoQuotesWithoutSpace,parserTwoQuotesWithoutSpace);

        String[] parserTwoQuotesWithSpace =  parse(" echo \" \"\" \"");
        String[] parserValidTwoQuotesWithSpace = new String[] {"echo","  "};
        assertArrayEquals(parserTwoQuotesWithSpace,parserValidTwoQuotesWithSpace);

        String[] parserAloneQuotesWithSpace =  parse(" echo \" ' \"");
        String[] parserValidAloneQuotesWithSpace = new String[] {"echo"," ' "};
        assertArrayEquals(parserValidAloneQuotesWithSpace,parserAloneQuotesWithSpace);

        String[] parserTwoAloneQuotesWithSpace =  parse(" echo \" ' ' \"");
        String[] parserValidTwoAloneQuotesWithSpace = new String[] {"echo"," ' ' "};
        assertArrayEquals(parserValidTwoAloneQuotesWithSpace,parserTwoAloneQuotesWithSpace);

        String[] parserAloneObliqueQuotesWithoutSpace =  parse(" echo \"`\"");
        String[] parserValidAloneObliqueQuotesWithoutSpace = new String[] {"echo","`"};
        assertArrayEquals(parserValidAloneObliqueQuotesWithoutSpace,parserAloneObliqueQuotesWithoutSpace);

        String[] parserTwoObliqueQuotesWithoutSpace =  parse(" echo \"``\"");
        String[] parserValidTwoObliqueQuotesWithoutSpace = new String[] {"echo","``"};
        assertArrayEquals(parserValidTwoObliqueQuotesWithoutSpace,parserTwoObliqueQuotesWithoutSpace);
    }

    @Test
    public void  testDollar(){

        String[] parserDollarWithoutSpace =  parse(" echo \"$\"");
        String[] parserValidDollarWithoutSpace = new String[] {"echo","$"};
        assertArrayEquals(parserValidDollarWithoutSpace,parserDollarWithoutSpace);
    }
}
