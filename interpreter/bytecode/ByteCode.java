package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public abstract class ByteCode {
    protected String name = "";

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
     * This abstract method is to return ByteCode in string
     *
     * @param vm VirtualMachine for calling runStack methods for dumping
     * @return string of ByteCode
     */
    public abstract String toString(VirtualMachine vm);
}
