package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class WriteCode extends ByteCode {
    public void init(ArrayList args) {
        this.name = "WRITE";
        // check if there is no argument
        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        System.out.println(vm.peekRunStack());
    }

    public String toString() {
        return this.name;
    }
}
