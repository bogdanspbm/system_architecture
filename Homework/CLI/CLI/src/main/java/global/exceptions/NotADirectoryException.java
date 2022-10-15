package global.exceptions;

public class NotADirectoryException extends CommandRuntimeException {
    private final static String ERROR_MESSAGE_PATTERN = "%s: %s: Not a directory";

    public NotADirectoryException(String command, String filename) {
        super(String.format(ERROR_MESSAGE_PATTERN, command, filename));
    }
}
