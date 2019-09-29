package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;

public class VirtualMachine {
    // Used to store all variables in program.
    private RunTimeStack   runStack;

    // Used to store return addresses for each called function (excluding main)
    private Stack<Integer> returnAddrs;

    // Reference to the program object where all bytecodes are stored.
    private Program        program;

    // the program counter (current bytecode being executed).
    private int            pc;

    // Used to determine whether the VirtualMachine should be executing bytecodes.
    private boolean        isRunning;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    // TODO SAMPLE INCOMPLETE
    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack <Integer>();
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            pc++;
        }
    }
}
