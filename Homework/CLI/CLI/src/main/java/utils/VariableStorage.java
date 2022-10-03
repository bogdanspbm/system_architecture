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

    public void put(String key, String value) {
        valuesMap.put(key, value);
    }

    public String get(String key) {
        return valuesMap.get(key);
    }

    public String replaceKeys(String str){

        if(str.contains("$")){

            if(str.startsWith("$")){
                str = str.substring( 1);
            }

        String[] keys = str.split("\\$");

        String result = "";

        for(String key : keys){
            if(valuesMap.containsKey(key)){
                result += valuesMap.get(key);
            } else {
                result += "$" + key;
            }
        }

        return result;}else{
            return str;
        }
    }

}
