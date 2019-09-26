package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
    public void init(ArrayList args) {
        // TODO
        this.name = "LABEL";
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }

    public String toString() {
        // TODO
        return "";
    }

    public String getLabel() {
        return this.label;
    }
}
