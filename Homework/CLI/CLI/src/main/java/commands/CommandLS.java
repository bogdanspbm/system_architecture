package commands;

import global.SharedVariables;

import java.io.File;

public class CommandLS extends Command {
    public CommandLS(String name) {
        super(name);


    }

    @Override
    public void hiddenExecute() {
        System.out.println(buildOutput());
    }

    @Override
    public String buildOutput() {
        StringBuilder result = new StringBuilder();
        File dir = new File(SharedVariables.getCurPath());
        for (File file : dir.listFiles()) {
            result.append(file.getName());
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
