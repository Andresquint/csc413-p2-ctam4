package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

public class CallCode extends SymbolicByteCode {
    public void init(ArrayList args) {
        this.name = "CALL";
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.setAddress((String) args.get(0));
    }

    public void execute(VirtualMachine vm) {
        // push current program counter to returnAddres
        vm.pushReturnAddress(vm.getPC());
        // set current program counter to resolved index
        try {
            vm.setPC(this.getIndex() - 1);
        }
        // happens if address is not resolved
        catch (IndexOutOfBoundsException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
    }

    @Override
    public String toString(VirtualMachine vm) {
        try {
            return this.name + " " + this.getAddress() + "   " + this.getAddress().split("<<")[0] + "(" + vm.dumpFrameRunStack(vm.getFrameSizeRunStack()) + ")";
        }
        // happens if address is not resolved
        catch (IndexOutOfBoundsException e) {
            return this.name + " " + this.getAddress();
        }
    }
}
