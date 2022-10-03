package commands;

import global.GlobalFunctions;
import global.SharedVariables;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandWC extends Command {
    public CommandWC(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
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
                List<String> content = new ArrayList<>();
                content.add(params.get(0));
                int wordsCount = GlobalFunctions.getWordsCount(content);
                return content.size() + " " + wordsCount + " " + file.length() + " ";
            }
        }
        return "Wrong argument";
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
