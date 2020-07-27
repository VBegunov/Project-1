package tusk2.core;

import tusk2.exceptions.InvalidFileReadException;

import java.io.File;

public class CheckArgument {
    public static void checkArg(String[] args) {
        //проверка на аргументы
        if (args.length == 0) {
            throw new InvalidFileReadException("Не указаны адерса для считывания файлов");
        }

        if (args.length == 1) {
            throw new InvalidFileReadException("Отсутствует адрес для второго файла");
        }
    }
}
