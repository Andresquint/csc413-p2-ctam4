package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode {
    public void init(ArrayList args) {
        this.name = "READ";
        // check if there is no argument
        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        // create Scanner object from System.in
        Scanner scanner = new Scanner(System.in);
        // keep retrying until user enter an integer
        System.out.print("Enter an integer: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Not an integer! Try again: ");
            scanner.next();
        }
        vm.pushRunStack(scanner.nextInt());
        // close Scanner
        scanner.close();
    }

    public String toString(VirtualMachine vm) {
        return this.name;
    }
}
