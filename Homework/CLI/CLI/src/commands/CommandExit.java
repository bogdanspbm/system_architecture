package commands;

public class CommandExit extends Command {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
