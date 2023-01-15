package factory;

// import commands.*;
import commands.Command;
import commands.CommandCat;
import commands.CommandCd;
import commands.CommandEcho;
import commands.CommandExit;
import commands.CommandLS;
import commands.CommandOut;
import commands.CommandPWD;
import commands.CommandWC;

public class CommandFactory {

    // Фактори возрващающий экземпляр команды, построенной по строковому ключу
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
            case "cd": {
                return new CommandCd(name);
            }
            case "exit": {
                return new CommandExit(name);
            }
        }
        return new CommandOut(name);
    }
}
