package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int number;

    public void init(ArrayList args) {
        this.name = "ARGS";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.number = Integer.parseInt((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        vm.newFrameAtRunStack(this.number);
    }

    public String toString(VirtualMachine vm) {
        return this.name + " " + this.number;
    }
}
