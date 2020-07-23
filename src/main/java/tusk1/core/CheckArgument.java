package tusk1.core;

import tusk1.exceptions.InvalidFileNameReadException;
import tusk1.exceptions.InvalidFileNameWriteException;

import java.io.File;

//  C:\Project\src\main\java\tusk1\testFiles\var.txt C:\Project\src\main\java\tusk1\testFiles\Варианты.txt

public class CheckArgument {

    public static void checkArg(String[] args) throws InvalidFileNameReadException, InvalidFileNameWriteException {
        //проверка на аргументы
        if (args.length == 0) {
            throw new InvalidFileNameReadException("Не указан файл для считывания сотрудников");
        }

        if (args.length == 1) {
            throw new InvalidFileNameWriteException("Отсутствует адрес для записи вариантов перевода");
        }

        if (args.length == 2) {
            if (!new File(args[1]).isDirectory()) {
                throw new InvalidFileNameWriteException("Некорректно указан каталог для записи вариантов перевода " + args[1]);
            }
        }


    }
}
