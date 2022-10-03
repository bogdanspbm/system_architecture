package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack {
    private static Stack singleton;
    private List<String> stack = new ArrayList<>();

    private String lastOutput = "";

    private Stack() {

    }

    // Возвращается singleton стэка
    public static synchronized Stack getStack() {
        if (singleton == null) {
            singleton = new Stack();
        }

        return singleton;
    }

    public void setLastOutput(String output) {
        this.lastOutput = output;
    }

    // Добавляет одно слово в конец стэка
    public void put(String word) {
        stack.add(word);
    }

    private void putPipe(String word) {
        stack.add(nextPipeIndex() + 1, word);
    }

    public void pipeLastOutput() {
        putPipe(lastOutput);
    }

    private int nextPipeIndex() {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).equals("|")) {
                return i;
            }
        }
        return stack.size() - 1;
    }

    // Добавляет массив слов в конец стэка
    public void putArray(String[] word) {
        stack.addAll(Arrays.asList(word));
    }

    // Показывает, пуст ли стэк
    public boolean hasNext() {
        return stack.size() > 0;
    }

    public boolean isNextPipe() {
        return stack.size() > 0 && stack.get(0).equals("|");
    }

    // Показывает, есть ле еще слова до пайпа
    public boolean hasNextParam() {
        return stack.size() > 0 && !stack.get(0).equals("|");
    }

    // Возвращает значение одного слова из начала стэка, удаляя его
    public String get() {
        if (stack.size() == 0) {
            return "";
        }

        String res = stack.get(0);
        stack.remove(0);

        res = VariableStorage.getStorage().replaceKeys(res);

        return res;
    }
}
