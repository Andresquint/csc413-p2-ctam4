package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class HaltCode extends ByteCode {
    public void init(ArrayList args) {
        this.name = "HALT";
        // check if there is no argument
        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }

    public String toString() {
        return this.name;
    }
}
