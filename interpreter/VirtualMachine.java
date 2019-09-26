package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;

public class VirtualMachine {
    private RunTimeStack   runStack;
    private Stack<Integer> returnAddrs;
    private Program        program;
    private int            pc;
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
