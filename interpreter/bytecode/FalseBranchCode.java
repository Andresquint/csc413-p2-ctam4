package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class FalseBranchCode extends SymbolicByteCode {
    public void init(ArrayList args) {
        this.name = "FALSEBRANCH";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.setAddress((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        // pop the top of stack
        try {
            // check if the top of stack is 0
            if (vm.popRunStack() == 0) {
                vm.setPC(this.getIndex() - 1);
            }
        }
        // happens if stack is empty or address is not resolved
        catch (EmptyStackException | IndexOutOfBoundsException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
    }
}
