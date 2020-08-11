package task2.exceptions;

public class InvalidFileReadException extends RuntimeException {
    public InvalidFileReadException(String s) {
        super(s);
    }
}
