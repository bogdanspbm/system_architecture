package commands;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import global.exceptions.FileNotFoundException;
import global.exceptions.NotADirectoryException;
import global.SharedVariables;
import utils.VariableStorage;

public class CommandCD extends Command {
    private String path;

    public CommandCD(String name) {
        super(name);

        if (!params.isEmpty()) {
            this.path = params.get(0);
        } else {
          this.path = null;
        }
    }

    @Override
    public void hiddenExecute() {
        executeCommand();
    }

    // Provided implementation is using the following algorithm: https://man7.org/linux/man-pages/man1/cd.1p.html,
    // thus on UNIX system it's considered to be POSIX-compliant.
    private void executeCommand() {
      VariableStorage variableStorage = VariableStorage.getStorage();

      if (this.path == null && !variableStorage.hasKey("HOME")) {
          // POSIX defines undefined behaviour, we prefer to simply skip.
      } else if (this.path == null) {
          updateCurrentPath(variableStorage.get("HOME"));
      } else {
        Path targetPath = this.targetPath();
        File f = new File(targetPath.toString());

        if (!f.exists()) {
            throw new FileNotFoundException(this.name, this.path);
        } else if (!f.isDirectory()) {
            throw new NotADirectoryException(this.name, this.path);
        }

        updateCurrentPath(targetPath().toString());
      }
    }

    private Path targetPath() {
      if (this.path == null) { return null; }

      Path parsedPath = Paths.get(this.path);
      if (parsedPath.isAbsolute()) {
        return parsedPath;
      } else {
        Path currentPath = Paths.get(SharedVariables.getCurPath());
        Path newPath = currentPath.resolve(this.path);

        return newPath;
      }
    }

    private void updateCurrentPath(String path) {
      SharedVariables.setCurPath(path);
    }

    @Override
    public int getParamsCount() {
        return -1;
    }

    @Override
    public String buildOutput() {
      return new String();
    }
}
