package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class PopCode extends ByteCode {
    private int number;

    public void init(ArrayList args) {
        this.name = "POP";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.number = Integer.parseInt((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }

    public String toString() {
        return this.name + " " + this.number;
    }
}
