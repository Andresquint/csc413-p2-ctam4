package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class ReturnCode extends SymbolicByteCode {
    public void init(ArrayList args) {
        // TODO
        this.name = "RETURN";
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }
}
