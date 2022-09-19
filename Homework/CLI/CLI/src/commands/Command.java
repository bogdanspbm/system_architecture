package commands;

import utils.Stack;

import java.util.ArrayList;
import java.util.List;

import static utils.Stack.getStack;

public abstract class Command {

    List<String> params = new ArrayList<>();

    public Command() {
        readParams();
    }

    public abstract void execute();

    public abstract int getParamsCount();

    private void readParams() {
        Stack stack = getStack();
        for (int i = 0; i < getParamsCount() && stack.hasNext(); i++) {
            params.add(stack.get());
        }
    }
}
