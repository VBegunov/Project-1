package task1;

import task1.core.CheckArgument;
import task1.core.ReadsEmployees;
import task1.core.TransferEmployees;
import task1.core.WriteTransferEmployees;

public class Solution {
    public static void main(String[] args) {
        //проверка на аргументы ???сделать исключения проверяемыми или не проверяемыми???
        CheckArgument.checkArg(args);

        ReadsEmployees read = new ReadsEmployees(args[0]);
        read.createDepartments();

        TransferEmployees transferEmployees = new TransferEmployees(read.getDepartments());

        System.out.println(read.getInvalidEmployees());
        System.out.println(read.getDepartments().toString());

        WriteTransferEmployees writeTransferEmployees = new WriteTransferEmployees(args[1]);
        writeTransferEmployees.write(transferEmployees.transferEmployees());

    }
}




