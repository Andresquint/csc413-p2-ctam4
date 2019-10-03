package interpreter;

import interpreter.bytecode.ByteCode;
import java.util.EmptyStackException;
import java.util.Stack;

public class VirtualMachine {
    // Used to store all variables in program.
    private RunTimeStack   runStack;

    // Used to store return addresses for each called function (excluding main)
    private Stack<Integer> returnAddress;

    // Reference to the program object where all bytecodes are stored.
    private Program        program;

    // the program counter (current bytecode being executed).
    private int            pc;

    // Used to determine whether the VirtualMachine should be executing bytecodes.
    private boolean        isRunning;

    // Used to determine whether dump status
    private boolean        isDumping;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {
        this.pc = 0;
        this.runStack = new RunTimeStack();
        this.returnAddress = new Stack<Integer>();
        this.isRunning = true;
        this.isDumping = false;
        while (this.isRunning) {
            ByteCode code = this.program.getCode(this.pc);
            code.execute(this);
            if (this.isDumping) {
                System.out.println(code.toString(this));
                // check if ByteCode is not DUMP
                if (!code.getClass().getSimpleName().equals("DumpCode")) {
                    // dump runStack
                    System.out.println(this.dumpRunStack());
                }
            }
            this.pc++;
        }
    }

    /**
     * This function pauses the program
     */
    public void stopRunning() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }

    /**
     * This function starts dumping runStack of the program
     */
    public void startDumping() {
        if (!this.isDumping) {
            this.isDumping = true;
        }
    }

    /**
     * This function stops dumping runStack of the program
     */
    public void stopDumping() {
        if (this.isDumping) {
            this.isDumping = false;
        }
    }

    /**
     * This function pushes pc to returnAddress stack
     *
     * @param  pc program counter
     * @return value pushed
     */
    public int pushReturnAddress(int pc) {
        this.returnAddress.push(pc);
        return this.returnAddress.peek();
    }

    /**
     * This function pops the returnAddress stack
     *
     * @return the value popped
     */
    public int popReturnAddress() {
        // check if returnAddress is empty
        if (this.returnAddress.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.returnAddress.pop();
    }

    /**
     * This function sets program counter to specific Value
     *
     * @param pc program counter
     */
    public void setPC(int pc) {
        this.pc = pc;
    }

    /**
     * This function returns current program counter
     *
     * @return current program counter
     */
    public int getPC() {
        return this.pc;
    }

    private String dumpRunStack() {
        return this.runStack.dump();
    }

    public int peekRunStack() {
        return this.runStack.peek();
    }

    public int pushRunStack(int i) {
        return this.runStack.push(i);
    }

    public int popRunStack() {
        return this.runStack.pop();
    }

    public int storeRunStack(int offset) {
        return this.runStack.store(offset);
    }

    public int loadRunStack(int offset) {
        return this.runStack.load(offset);
    }

    public int getFrameSizeRunStack() {
        return this.runStack.getFrameSize();
    }

    public String dumpFrameRunStack(int i) {
        return this.runStack.dumpFrame(i);
    }

    public void newFrameAtRunStack(int offset) {
        this.runStack.newFrameAt(offset);
    }

    public void popFrameRunStack() {
        this.runStack.popFrame();
    }
}
