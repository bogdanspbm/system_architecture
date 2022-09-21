package factory;

import commands.*;

public class CommandFactory {
    public Command createCommand(String name) {
        switch (name) {
            case "cat": {
                return new CommandCat(name);
            }
            case "echo": {
                return new CommandEcho(name);
            }
            case "wc": {
                return new CommandWC(name);
            }
            case "pwd": {
                return new CommandPWD(name);
            }
            case "ls": {
                return new CommandLS(name);
            }
            case "exit": {
                return new CommandExit(name);
            }
        }
        return new CommandOut(name);
    }
}
