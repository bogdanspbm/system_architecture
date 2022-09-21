package commands;

import global.GlobalFunctions;
import global.SharedVariables;

import java.io.File;
import java.util.List;

public class CommandCat extends Command {
    public CommandCat(String name) {
        super(name);
    }

    @Override
    public void execute() {
        if (params.size() == 1) {
            String fileName = params.get(0);
            File file = new File(SharedVariables.curPath + "/" + fileName);
            if (file.exists()) {
                List<String> content = GlobalFunctions.getFileContent(file);
                for (String line : content) {
                    System.out.println(line);
                }
            }
        }
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
