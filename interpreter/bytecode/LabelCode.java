package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
    private String label;

    public void init(ArrayList args) {
        this.name = "LABEL";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.label = (String) args.get(0);
    }

    public void execute(VirtualMachine vm) {}

    public String toString() {
        return this.name + " " + this.getLabel();
    }

    public String getLabel() {
        return this.label;
    }
}
