package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class LitCode extends ByteCode {
    private int value;
    private String id = null;

    public void init(ArrayList args) {
        this.name = "LIT";
        // check if there is no argument, more than 2 arguments or null
        if (args == null || args.size() < 1 || args.size() > 2) {
            throw new IllegalArgumentException();
        }
        switch (args.size()) {
            case 1:
                this.value = Integer.parseInt((String) args.get(0));
                break;
            case 2:
                this.value = Integer.parseInt((String) args.get(0));
                this.id = (String) args.get(1);
                break;
        }
    }

    public void execute(VirtualMachine vm) {
        // if there is one argument
        if (this.id == null) {
            // push to runStack with 0
            vm.pushRunStack(0);
        }
        // if there are two arguments
        else {
            // push to runStack with value
            vm.pushRunStack(this.value);
        }
    }

    public String toString(VirtualMachine vm) {
        // if there is one argument
        if (this.id == null) {
            return this.name + " " + this.value;
        }
        // if there are two arguments
        else {
            return this.name + " " + this.value + " " + this.id;
        }
    }
}
