package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class HaltCode extends ByteCode {
    public void init(ArrayList args) {
        // TODO
        this.name = "HALT";
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }

    public String toString() {
        // TODO
        return "";
    }
}
