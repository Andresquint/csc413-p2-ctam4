package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private boolean status;

    public void init(ArrayList args) {
        this.name = "DUMP";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        // check if argument is "ON"
        if (((String) args.get(0)).equals("ON")) {
            this.status = true;
        }
        // check if argument is "OFF"
        else if (((String) args.get(0)).equals("OFF")) {
            this.status = false;
        }
        // unsupported argument
        else {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        // if status is TRUE
        if (this.status) {
            vm.startDumping();
        }
        // if status is FALSE
        else {
            vm.stopDumping();
        }
    }

    public String toString() {
        // if status is TRUE
        if (this.status) {
            return this.name + " ON";
        }
        // if status is FALSE
        else {
            return this.name + " OFF";
        }
    }
}
