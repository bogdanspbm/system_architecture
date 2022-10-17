package global.exceptions;

public class FileNotFoundException extends CommandRuntimeException {
    private final static String ERROR_MESSAGE_PATTERN = "%s: %s: No such file or directory";

    public FileNotFoundException(String command, String filename) {
        super(String.format(ERROR_MESSAGE_PATTERN, command, filename));
    }
}
