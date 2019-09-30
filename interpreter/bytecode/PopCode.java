package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class PopCode extends ByteCode {
    private int number;

    public void init(ArrayList args) {
        this.name = "POP";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.number = Integer.parseInt((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        // pop the top number levels of the runStack
        try {
            for (int i = 0; i < this.number; i++) {
                vm.popRunStack();
            }
        }
        // happens if stack is empty
        catch (EmptyStackException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
    }

    public String toString() {
        return this.name + " " + this.number;
    }
}
