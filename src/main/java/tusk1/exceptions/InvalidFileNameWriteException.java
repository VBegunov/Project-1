package tusk1.exceptions;

import org.w3c.dom.ls.LSOutput;

public class InvalidFileNameWriteException extends RuntimeException {
    public InvalidFileNameWriteException(String s) {
        super(s);
    }
}
