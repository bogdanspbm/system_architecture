package global.exceptions;

public class CommandRuntimeException extends RuntimeException {
    public CommandRuntimeException(String message) {
        super(message);
    }
}
