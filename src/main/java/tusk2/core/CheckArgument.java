package tusk2.core;

import tusk2.exceptions.InvalidFileReadException;

public class CheckArgument {
    public static void checkArg(String[] args) {
        if (args.length == 0) {
            throw new InvalidFileReadException("Не указаны адерса для считывания файлов");
        }

        if (args.length == 1) {
            throw new InvalidFileReadException("Отсутствует адрес второго файла");
        }
    }
}
