package commands;

import java.io.File;
import java.util.Arrays;

import global.SharedVariables;

public class CommandCd extends Command {
    public CommandCd(String name) {
        super(name);
    }

    @Override
    public void execute() {
        String output = buildOutput();
        if (output != "") { 
            System.out.println(output);
        }
    }

    @Override
    public String buildOutput() {
        if (params.size() == 0) { 
            String homePath = System.getProperty("user.home");
            SharedVariables.setCurPath(homePath);
            return "";
        }
        if (params.size() != 1) { 
            return "Wrong argument";
        }

        String destDir = params.get(0);
        File destFile;
        if (destDir.charAt(0) == '/') { 
            destFile = new File(destDir);
        } else { 
            destFile = new File(SharedVariables.getCurPath() + "/" + destDir);
        }

        if (!destFile.exists()) { 
            return "Directory not found";
        }
        if (!destFile.isDirectory()) { 
            return "Not a directory";
        }
        try { 
            SharedVariables.setCurPath(destFile.getCanonicalPath());
        } catch(Exception e) { 
            return "Error: " + e.getMessage();
        }

        return "";
    }

    @Override
    public int getParamsCount() {
        return 1;
    }
}
