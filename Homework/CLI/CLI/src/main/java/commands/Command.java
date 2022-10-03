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

    // Абстрактный метод вызова команды
    public void execute() {
        getStack().setLastOutput(buildOutput());
        hiddenExecute();
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
