package task1.exceptions;

public class InvalidFileNameReadException extends RuntimeException {
    public InvalidFileNameReadException(String s) {
        super(s);
    }
}
