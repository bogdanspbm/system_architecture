package utils;

public class Parser {

    // Разбивает входную строку на слова
    // В первой версии по пробелам
    // В будущей версии также будет учтен другой синтаксис
    public static String[] parse(String line) {
        String[] words = line.split(" ");



        for(int i = 0; i < words.length;i++){
            words[i] = VariableStorage.getStorage().replaceKeys(words[i]);
        }

        return words;
    }
}
