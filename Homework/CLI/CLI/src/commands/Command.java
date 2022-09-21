package commands;

import utils.Stack;

import java.util.ArrayList;
import java.util.List;

import static utils.Stack.getStack;

public abstract class Command {

    String name = "";
    List<String> params = new ArrayList<>();

    public Command(String name) {
        this.name = name;
        readParams();
    }

    public abstract void execute();

    public abstract int getParamsCount();

    private void readParams() {
        Stack stack = getStack();

        if (getParamsCount() != -1) {
            for (int i = 0; i < getParamsCount() && stack.hasNext(); i++) {
                params.add(stack.get());
            }
        } else {
            while (stack.hasNext()) {
                String param = stack.get();
                params.add(param);
            }
        }
    }
}
