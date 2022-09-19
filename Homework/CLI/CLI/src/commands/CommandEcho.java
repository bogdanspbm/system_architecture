package commands;

public class CommandEcho extends Command {
    @Override
    public void execute() {
        if (params.size() == 1) {
            System.out.println(params.get(0));
        }
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
