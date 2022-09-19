package utils;

import commands.Command;
import factory.CommandFactory;

import java.util.Scanner;

import static utils.Stack.getStack;

public class Reader {

    Scanner scan = new Scanner(System.in);
    CommandFactory factory = new CommandFactory();
    String lastLine = "";

    public Reader() {
    }

    public void start() {
        while (scan.hasNextLine()) {
            lastLine = scan.nextLine();
            getStack().putArray(Parser.parse(lastLine));
            while (getStack().hasNext()) {
                Command cmd = factory.createCommand(getStack().get());
                cmd.execute();
            }
        }
    }


}
