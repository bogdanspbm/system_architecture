package factory;

import commands.*;

public class CommandFactory {
    public Command createCommand(String name) {
        switch (name) {
            case "cat": {
                return new CommandCat();
            }
            case "echo": {
                return new CommandEcho();
            }
            case "wc": {
                return new CommandWC();
            }
            case "pwd": {
                return new CommandPWD();
            }
            case "ls": {
                return new CommandLS();
            }
            case "exit": {
                return new CommandExit();
            }
        }
        return new CommandNull();
    }
}
