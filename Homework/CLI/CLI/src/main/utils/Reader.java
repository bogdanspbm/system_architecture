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

    // Считывает команды из консоли
    // Каждую введенную команду парсит и помещает в стэк
    // Пробегается по стеку, пока не опустошит его, создавая и вызывая команды
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
