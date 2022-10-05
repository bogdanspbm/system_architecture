package commands;

import utils.STDIn;
import utils.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Stack.getStack;

public abstract class Command {

    private int optionsToAdd = 0;

    String name = "";
    List<String> params = new ArrayList<>();
    List<String> options = new ArrayList<>();

    Map<String, Integer> optionMap = new HashMap<>();

    public Command(String name) {
        this.name = name;
        generateOptionsMap();
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

    protected void generateOptionsMap() {

    }

    private void readParams() {
        Stack stack = getStack();
        STDIn stdin = STDIn.getSTDIn();

        String param = "";

        if (getParamsCount() != -1) {
            int i = 0;
            for (; i < getParamsCount() && stack.hasNext(); i++) {
                param = stack.get();
                i = addParamOrOption(param, i);
            }
            for (; i < getParamsCount() && stdin.hasNext(); i++) {
                param = stdin.get();
                i = addParamOrOption(param, i);
            }
        } else {
            while (stack.hasNextParam()) {
                param = stack.get();
                params.add(param);
            }
        }
    }

    private int addParamOrOption(String param, int i) {
        if (param.startsWith("-") || optionsToAdd > 0) {
            i = i - 1;

            if (optionMap.containsKey(param) && optionsToAdd == 0) {
                optionsToAdd += optionMap.get(param);
            } else if (optionsToAdd > 0) {
                optionsToAdd--;
            }

            options.add(param);
        } else {
            params.add(param);
        }

        return i;
    }
}
