package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class STDIn {
    private static STDIn singleton;

    private List<String> stack = new ArrayList<>();

    private STDIn() {

    }

    public static synchronized STDIn getSTDIn() {
        if (singleton == null) {
            singleton = new STDIn();
        }

        return singleton;
    }

    public String get() {
        if (stack.size() == 0) {
            return "";
        }

        String res = stack.get(0);
        stack.remove(0);

        res = VariableStorage.getStorage().replaceKeys(res);

        return res;
    }

    public void put(String word) {
        stack.add(word);
    }

    public void clear() {
        stack.clear();
    }

    public void putArray(String[] word) {
        stack.addAll(Arrays.asList(word));
    }

    public boolean hasNext() {
        return stack.size() > 0;
    }
}
