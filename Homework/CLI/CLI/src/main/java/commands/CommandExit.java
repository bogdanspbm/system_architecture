package commands;

public class CommandExit extends Command {
    public CommandExit(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
        System.exit(0);
    }

    @Override
    public String buildOutput() {
        return "0";
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
