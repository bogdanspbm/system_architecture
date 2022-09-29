package utils;

import java.util.HashMap;
import java.util.Map;

public class VariableStorage {

    private static VariableStorage singleton;

    private Map<String, String> valuesMap = new HashMap<>();

    private VariableStorage() {

    }

    public static synchronized VariableStorage getStorage() {
        if (singleton == null) {
            singleton = new VariableStorage();
        }
        return singleton;
    }

    private void put(String key, String value) {
        valuesMap.put(key, value);
    }

    private String get(String key) {
        return valuesMap.get(key);
    }


}
