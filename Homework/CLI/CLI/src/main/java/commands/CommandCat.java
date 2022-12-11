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
        System.out.println(buildOutput());
    }

    @Override
    public String buildOutput() {
        StringBuilder result = new StringBuilder();
        if (params.size() == 1) {
            String fileName = params.get(0);
            File file = new File(SharedVariables.getCurPath() + "/" + fileName);
            if (file.exists()) {
                List<String> content = GlobalFunctions.getFileContent(file);
                for (String line : content) {
                    result.append(line);
                    result.append("\n");
                }
            } else {
                result.append("File doesn't exist");
            }
        } else {
            result.append("Wrong argument");
        }
        return result.toString();
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
