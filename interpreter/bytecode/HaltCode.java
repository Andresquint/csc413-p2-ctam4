package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class HaltCode extends ByteCode {
    public void init(ArrayList args) {
        this.name = "HALT";
        // check if there is no argument
        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        vm.stopRunning();
    }

    public String toString() {
        return this.name;
    }
}
