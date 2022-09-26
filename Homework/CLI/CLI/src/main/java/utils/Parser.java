package utils;

public class Parser {

    // Разбивает входную строку на слова
    // В первой версии по пробелам
    // В будущей версии также будет учтен другой синтаксис
    public static String[] parse(String line) {
        return line.split(" ");
    }
}
