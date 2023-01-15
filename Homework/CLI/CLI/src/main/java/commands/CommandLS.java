package commands;

import global.SharedVariables;

import java.io.File;

public class CommandLS extends Command {
    public CommandLS(String name) {
        super(name);


    }

    @Override
    public void execute() {
        System.out.println(buildOutput());
    }

    @Override
    public String buildOutput() {
        if (params.size() > 1) {
            return "Wrong Argument";
        }
        Boolean allInfo = false;
        if (params.size() == 1) { 
            String flag = params.get(0);
            if (flag.equals("-a")) { 
                allInfo = true;
            } else { 
                return "Wrong Argument";
            }
        }

        StringBuilder result = new StringBuilder();
        File dir = new File(SharedVariables.getCurPath());
        for (File file : dir.listFiles()) {
            if (!file.isHidden() || allInfo) {
                result.append(file.getName());
                result.append("\n");
            }
        }
        if (allInfo) {
            result.append("..\n");
            result.append(".\n");
        }
        return result.toString();
    }

    @Override
    public int getParamsCount() {
        return -1;
    }
}
