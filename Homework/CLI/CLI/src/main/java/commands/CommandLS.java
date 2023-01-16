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
        if (params.size() > 2) {
            return "Wrong Argument";
        }
        String dirPath = SharedVariables.getCurPath();
        Boolean allInfo = false;
        if (params.size() == 1) { 
            String arg = params.get(0);
            if (arg.charAt(0) == '-') { 
                // is a flag
                if (arg.equals("-a")) { 
                    allInfo = true;
                } else { 
                    return "Wrong Argument";
                }
            } else { 
                dirPath = arg;
            }
        } else if (params.size() == 2) { 
            String flag = params.get(0);
            System.out.printf("flag: %s\n", flag);
            if (flag.equals("-a")) { 
                allInfo = true;
            } else { 
                return "Wrong Argument";
            }
            dirPath = params.get(1);
        }

        StringBuilder result = new StringBuilder();
        File dir = getFileFromPath(dirPath);
        if (!dir.exists() || !dir.isDirectory()) { 
            return "Invalid filepath";
        }
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

    private File getFileFromPath(String path) { 
        if (path.charAt(0) == '/') { 
            return new File(path);
        } else { 
            return new File(
                SharedVariables.getCurPath() + "/" + path
            );
        }
    }
}
