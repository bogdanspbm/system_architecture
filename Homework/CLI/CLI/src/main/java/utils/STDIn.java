package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class STDIn {
    private static STDIn singleton;

    private List<String> stack = new ArrayList<>();

    private STDIn() {

    }


    // Возвращает singleton хранилища stdin
    public static synchronized STDIn getSTDIn() {
        if (singleton == null) {
            singleton = new STDIn();
        }

        return singleton;
    }


    // Возвращает первое в очереди значение
    public String get() {
        if (stack.size() == 0) {
            return "";
        }

        String res = stack.get(0);
        stack.remove(0);

        res = VariableStorage.getStorage().replaceKeys(res);

        return res;
    }


    // Добавляет элемент в хранилище
    public void put(String word) {
        stack.add(word);
    }


    // Очищает хранилище
    public void clear() {
        stack.clear();
    }


    // Добавляет массив в хранилище
    public void putArray(String[] word) {
        stack.addAll(Arrays.asList(word));
    }


    // Возвращает информацию, пусто ли хранилище
    public boolean hasNext() {
        return stack.size() > 0;
    }
}
