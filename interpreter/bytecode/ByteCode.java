package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public abstract class ByteCode {
    /**
     * This abstract method is to initialize ByteCode
     *
     * @param args arguments for ByteCode
     */
    public abstract void init(ArrayList args);

    /**
     * This abstract method is to execute ByteCode
     *
     * @param vm VirtualMachine for calling other ByteCode
     */
    public abstract void execute(VirtualMachine vm);

    /**
     * This abstract method is to return ByteCode to compiled code
     *
     * @return string
     */
    public abstract String toString();
}
