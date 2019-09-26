package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class CallCode extends SymbolicByteCode {
    public void init(ArrayList args) {
        // TODO
        this.name = "CALL";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.setAddress((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }
}
