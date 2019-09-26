package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class WriteCode extends ByteCode {
    public void init(ArrayList args) {
        this.name = "WRITE";
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
