package main.utils;

import main.commands.Command;
import main.factory.CommandFactory;

import java.util.Scanner;

import static main.utils.Stack.getStack;

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
