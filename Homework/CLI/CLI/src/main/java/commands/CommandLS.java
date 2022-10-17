package commands;

import global.SharedVariables;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;

/** Class that represents ls command.*/
public class CommandLS extends Command {
    public CommandLS(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
        print(buildOutput());
    }

    @Override
    public String buildOutput() {
        StringBuilder files = new StringBuilder();
        StringBuilder folders = new StringBuilder();
        StringBuilder errors = new StringBuilder();

        if (params.size() == 0)
        {
            File dir = new File(SharedVariables.getCurPath());
            for (File file : dir.listFiles()) {
                files.append(file.getName());
                files.append(" ");
            }
            files.append("\n");
            return files.toString();
        }

        for (String fileName : params)
        {
            Path filePath = Paths.get(fileName);
            String fileNameAbsolute = filePath.toString();
            if (!filePath.isAbsolute()) 
            {
                fileNameAbsolute = Paths.get(SharedVariables.getCurPath(), fileName).toString();
                filePath = Paths.get(fileNameAbsolute);
            }
            if (!Files.exists(filePath))
            {
                errors.append(String.format("ls: cannot access '%s': No such file or directory\n", fileName));
                continue;
            }

            if (Files.isDirectory(filePath))
            {
                if (params.size() > 1)
                {
                    folders.append(fileName);
                    folders.append(":\n");
                }

                File dir = new File(fileNameAbsolute);
                for (File fileInFolder : dir.listFiles()) {
                    folders.append(fileInFolder.getName());
                    folders.append(" ");
                }
                folders.append("\n\n");
            }
            else
            {
                files.append(fileName);
                files.append(" ");
                continue;
            }
        }

        // make formating similar to UNIX ls 
        if (files.length() != 0)
        {
            files.append("\n");
            if (folders.length() != 0)
                files.append("\n");

        }
        if (folders.length() != 0)
            folders.deleteCharAt(folders.length() - 1);
            
        return errors.append(files).append(folders).toString();
    }

    @Override
    public int getParamsCount() {
        return -1;
    }
}
