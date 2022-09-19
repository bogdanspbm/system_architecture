package commands;

import global.SharedVariables;

public class CommandPWD extends Command {
    @Override
    public void execute() {
        System.out.println(SharedVariables.curPath);
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
