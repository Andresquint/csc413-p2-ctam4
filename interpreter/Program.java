package interpreter;

import java.util.ArrayList;

public class Program {
    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    /**
     * This function returns the ByteCode at a given index
     *
     * @param  pc index
     * @return ByteCode object
     */
    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     */
    public void resolveAddrs() {
        // TODO
    }
}
