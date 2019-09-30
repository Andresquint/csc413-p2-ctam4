package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class GotoCode extends SymbolicByteCode {
    public void init(ArrayList args) {
        this.name = "GOTO";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.setAddress((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        // set program counter to index
        try {
            vm.setPC(this.getIndex() - 1);
        }
        // happens if address is not resolved
        catch (IndexOutOfBoundsException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
    }
}
