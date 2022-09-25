package main.commands;

import main.global.GlobalFunctions;
import main.global.SharedVariables;

import java.io.File;
import java.util.List;

public class CommandWC extends Command {
    public CommandWC(String name) {
        super(name);
    }

    @Override
    public void execute() {
        System.out.println(buildOutput());
    }

    @Override
    public String buildOutput() {
        if (params.size() == 1) {
            String fileName = params.get(0);
            File file = new File(SharedVariables.getCurPath() + "/" + fileName);
            if (file.exists()) {
                List<String> content = GlobalFunctions.getFileContent(file);
                int wordsCount = GlobalFunctions.getWordsCount(content);
                return content.size() + " " + wordsCount + " " + file.length() + " ";
            } else {
                return "File doesn't exist";
            }
        }
        return "Wrong argument";
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
