package commands;

public class CommandAssign extends Command {

    public CommandAssign(String name) {
        super(name);
    }

    @Override
    public void execute() {

    }

    @Override
    public String buildOutput() {
        return null;
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
