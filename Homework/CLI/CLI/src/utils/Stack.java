package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack {
    private static Stack singleton;
    private List<String> stack = new ArrayList<>();

    private Stack() {

    }

    public static synchronized Stack getStack() {
        if (singleton == null) {
            singleton = new Stack();
        }

        return singleton;
    }

    public void put(String word) {
        stack.add(word);
    }

    public void putArray(String[] word) {
        stack.addAll(Arrays.asList(word));
    }

    public boolean hasNext() {
        return stack.size() > 0;
    }

    public String get() {
        if (stack.size() == 0) {
            return "";
        }

        String res = stack.get(0);
        stack.remove(0);

        return res;
    }
}
