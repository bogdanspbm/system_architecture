package main.commands;

import main.global.SharedVariables;

public class CommandPWD extends Command {
    public CommandPWD(String name) {
        super(name);

    }

    @Override
    public void execute() {
        System.out.println(buildOutput());
    }

    @Override
    public String buildOutput() {
        return SharedVariables.getCurPath();
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
