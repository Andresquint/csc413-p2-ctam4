package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class StoreCode extends ByteCode {
    private int value;
    private String id = null;

    public void init(ArrayList args) {
        this.name = "STORE";
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
        // pop the top of runSTack and store value to frame which value is the offset
        try {
            vm.storeRunStack(this.value);
        }
        // happens if stack is empty
        catch (EmptyStackException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
    }

    public String toString() {
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
