package utils;

import java.util.HashMap;
import java.util.Map;

public class VariableStorage {
    private static VariableStorage singleton;

    private Map<String, String> valuesMap = new HashMap<>();

    private final static String HOME_VARIABLE_NAME = "HOME";

    VariableStorage() {
        initializeDefaultVariables();
    }

    // Возвращает singleton хранилища переменных
    public static synchronized VariableStorage getStorage() {
        if (singleton == null) {
            singleton = new VariableStorage();
        }
        return singleton;
    }

    // Добавляет новую пару ключ значение в хранилище
    public void put(String key, String value) {
        valuesMap.put(key, value);
    }

    // Возвращает значение по ключу
    public String get(String key) {
        return valuesMap.get(key);
    }

    // Возвращает истинное значение тогда и только года, когда valuesMap содержит ключ key. 
    public boolean hasKey(String key) {
      return valuesMap.containsKey(key);
    }

    // Заменяет в строке все ключи на значения, если они есть
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

    private void initializeDefaultVariables() {
      valuesMap.put(HOME_VARIABLE_NAME, System.getProperty("user.home"));
    }
}
