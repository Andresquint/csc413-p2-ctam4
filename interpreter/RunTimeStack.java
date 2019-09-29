package interpreter;

import java.util.EmptyStackException;
import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {
    /**
     * This ArrayList is used to represent the runtime stack.
     * It will be an ArrayList because we will need to access
     * ALL locations of the runtime stack.
     */
    private ArrayList<Integer> runTimeStack;

    /**
     * This stack is used to record the beginning of
     * each activation record (frame) when calling functions.
     */
    private Stack<Integer>     framePointer;

    public RunTimeStack() {
        this.runTimeStack = new ArrayList<>();
        this.framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        this.framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack.
     * It will print portions of the stack based on respective
     * frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6,8
     */
    public void dump() {
        // TODO
    }

    /**
     * returns the top of the runtime stack, but does not remove
     *
     * @return copy of the top of the stack.
     */
    public int peek() {
        // check if runTimeStack is empty
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }

    /**
     * push the value i to the top of the stack.
     *
     * @param i value to be pushed.
     * @return value pushed
     */
    public int push(int i) {
        this.runTimeStack.add(i);
        return this.peek();
    }

    /**
     * removes to the top of the runtime stack.
     *
     * @return the value popped.
     */
    public int pop() {
        // check if runTimeStack is empty
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
    }

    /**
     * Takes the top item of the run time stack, and stores
     * it into a offset starting from the current frame.
     *
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
    public int store(int offset) {
        // check if runTimeStack is empty
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        int i = this.pop();
        this.runTimeStack.add(this.peek() - offset, i);
        return i;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of
     * the stack.
     *
     * @param offset number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offset) {
        // check if runTimeStack is empty
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.push(this.runTimeStack.get(this.framePointer.peek() - offset));
    }

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     *
     * @param offset slots down from the top of the runtime stack
     */
    public void newFrameAt(int offset) {
        this.framePointer.push(this.runTimeStack.size() - offset);
    }

    /**
     * pop the current frame off the runtime stack. Also removes
     * the frame pointer value from the FramePointer Stack.
     */
    public void popFrame() {
        int currentFrame = this.pop();
        int i = this.framePointer.pop();
        for (int j = this.runTimeStack.size() - 1; j >= i; j--) {
            this.pop();
        }
        this.push(currentFrame);
    }
}
