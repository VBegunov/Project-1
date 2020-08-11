package task1.exceptions;

public class InvalidFileNameWriteException extends RuntimeException {
    public InvalidFileNameWriteException(String s) {
        super(s);
    }
}
