package main.global;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GlobalFunctions {
    public static List<String> getFileContent(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static int getWordsCount(List<String> lines) {
        int count = 0;
        for (String line : lines) {
            String[] words = line.split(" ");
            count += words.length;
        }
        return count;
    }
}
