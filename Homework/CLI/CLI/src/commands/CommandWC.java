package commands;

import global.GlobalFunctions;
import global.SharedVariables;

import java.io.File;
import java.util.List;

public class CommandWC extends Command {
    public CommandWC(String name) {
        super(name);
    }

    @Override
    public void execute() {
        if (params.size() == 1) {
            String fileName = params.get(0);
            File file = new File(SharedVariables.curPath + "/" + fileName);
            if (file.exists()) {
                List<String> content = GlobalFunctions.getFileContent(file);
                int wordsCount = GlobalFunctions.getWordsCount(content);
                System.out.println(content.size() + " " + wordsCount + " " + file.length() + " ");
            }
        }
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
