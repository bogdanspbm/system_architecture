package commands;

import static utils.Stack.getStack;

public class CommandPipe extends Command {

    public CommandPipe(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
        getStack().pipeLastOutput();
    }

    @Override
    public String buildOutput() {
        return "";
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
