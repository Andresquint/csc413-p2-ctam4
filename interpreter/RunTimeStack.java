package interpreter;

import java.util.EmptyStackException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

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
     * This function returns size of runTimeStack
     *
     * @return size of runTimeStack ArrayList
     */
    public int getSize() {
        return this.runTimeStack.size();
    }

    /**
     * Used for dumping the current state of the runTimeStack.
     * It will print portions of the stack based on respective
     * frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6,8
     */
    public String dump() {
        try {
            if (this.getFrameSize() == 1) {
                return "[" + this.dumpFrame(1) + "]";
            } else {
                return this.dumpFrame(1);
            }
        }
        // happens if stack is empty
        catch (EmptyStackException | IllegalArgumentException e) {
            System.out.println("**** " + e);
            System.exit(-1);
            return "";
        }
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
        return this.runTimeStack.get(this.getSize() - 1);
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
        return this.runTimeStack.remove(this.getSize() - 1);
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
        this.runTimeStack.add(this.peek() + offset, i);
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
        return this.push(this.runTimeStack.get(this.peekFrame() + offset));
    }

    /**
     * This function returns size of framePointer
     *
     * @return size of framePointer Stack
     */
    public int getFrameSize() {
        return this.framePointer.size();
    }

    /**
     * This functions returns string of framePointer dump from layer i
     *
     * @param i starting layer of index
     * @return string of formatted elements of each layer separated with [] and space
     */
    public String dumpFrame(int i) {
        // check if framePointer is empty
        if (this.framePointer.isEmpty()) {
            throw new EmptyStackException();
        }
        // check if i is within size of framePointer Stack
        if (i < 1 || i > this.getFrameSize()) {
            throw new IllegalArgumentException();
        }
        String result = "";
        int nextFrame;
        for (int j = i - 1; j < this.getFrameSize(); j++) {
            if (j + 1 < this.getFrameSize()) {
                nextFrame = this.framePointer.get(j + 1);
            } else {
                nextFrame = this.getSize();
            }
            if (i != this.getFrameSize()) {
                result += "[";
            }
            result += this.runTimeStack.subList(this.framePointer.get(j), nextFrame).stream().map(n -> n.toString()).collect(Collectors.joining(","));
            if (i != this.getFrameSize()) {
                result += "]";
            }
            if (j + 1 < this.getFrameSize()) {
                result += " ";
            }
        }
        return result;
    }

    /**
     * This function returns the top value of framePointer Stack
     *
     * @return top value of framePointer Stack
     */
    public int peekFrame() {
        // check if framePointer is empty
        if (this.framePointer.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.framePointer.peek();
    }

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     *
     * @param offset slots down from the top of the runtime stack
     */
    public void newFrameAt(int offset) {
        this.framePointer.push(this.getSize() - offset);
    }

    /**
     * pop the current frame off the runtime stack. Also removes
     * the frame pointer value from the FramePointer Stack.
     */
    public void popFrame() {
        int lastValue = this.peek();
        int i = this.framePointer.pop();
        for (int j = this.getSize() - 1; j >= i; j--) {
            this.runTimeStack.remove(j);
        }
        this.push(lastValue);
    }
}
