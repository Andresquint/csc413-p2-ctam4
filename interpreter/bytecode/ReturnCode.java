package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class ReturnCode extends SymbolicByteCode {
    public void init(ArrayList args) {
        this.name = "RETURN";
        // check if there is only one argument
        if (args != null && args.size() != 1) {
            throw new IllegalArgumentException();
        }
        if (args != null) {
            this.setAddress((String) args.get(0));
        }
    }

    public void execute(VirtualMachine vm) {
        // pop the current frame from runStack and set return address to current program coutner
        try {
            vm.popFrameRunStack();
            vm.setPC(vm.popReturnAddress());
        }
        // happens if stack is empty
        catch (EmptyStackException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
    }
}
