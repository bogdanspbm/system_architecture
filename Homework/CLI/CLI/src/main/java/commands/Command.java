package commands;

import utils.STDIn;
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

    // Абстрактный метод вызова команды
    public void execute() {
        hiddenExecute();
        getStack().setLastOutput(buildOutput());
    }

    protected void print(String text) {
        if (!getStack().isNextPipe()) {
            System.out.println(text);
        }
    }

    abstract void hiddenExecute();

    // Абстрактный метод, генерирующий строковой вывод исполнения команды
    public abstract String buildOutput();

    // Абстрактный метод, возвращающий количество параметров, которое требуется для исполнения команды
    // -1 - любое количество
    // 0 - inf - точное количество
    public abstract int getParamsCount();

    private void readParams() {
        Stack stack = getStack();
        STDIn stdin = STDIn.getSTDIn();

        if (getParamsCount() != -1) {
            int i = 0;
            for (; i < getParamsCount() && stack.hasNext(); i++) {
                params.add(stack.get());
            }
            for (; i < getParamsCount() && stdin.hasNext(); i++) {
                params.add(stdin.get());
            }
        } else {
            while (stack.hasNextParam()) {
                String param = stack.get();
                params.add(param);
            }
        }
    }
}
