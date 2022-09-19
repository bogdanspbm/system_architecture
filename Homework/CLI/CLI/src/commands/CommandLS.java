package commands;

import global.SharedVariables;

import java.io.File;

public class CommandLS extends Command {
    @Override
    public void execute() {
        File dir = new File(SharedVariables.curPath);
        for(File file : dir.listFiles()){
            System.out.println(file.getName());
        }
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
