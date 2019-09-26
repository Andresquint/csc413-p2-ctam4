package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.SymbolicByteCode;
import interpreter.bytecode.LabelCode;

public class Program {
    private ArrayList<ByteCode> program;

    public Program() {
        this.program = new ArrayList<>();
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
     * This function puts ByteCode to program ArrayList
     *
     * @param bc ByteCode instance
     */
    protected void addCode(ByteCode bc) {
        this.program.add(bc);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     */
    public void resolveAddress() {
        // check if program ArrayList is not empty
        if (!this.program.isEmpty()) {
            HashMap<String,Integer> labels = new HashMap<>();
            ArrayList<Integer> pending = new ArrayList<>();

            // check every items on program ArrayList
            for (int i = 0; i < this.program.size(); i++) {
                switch (this.program.get(i).getClass().getSimpleName()) {
                    case "CallCode":
                    case "FalseBranchCode":
                    case "GoToCode":
                        pending.add(i);
                        break;
                    case "LabelCode":
                        LabelCode bc = (LabelCode) this.program.get(i);
                        labels.put(bc.getLabel(), i);
                        break;
                }
            }

            // replace symbolic address
            SymbolicByteCode bc;
            for (int i : pending) {
                bc = (SymbolicByteCode) this.program.get(i);
                bc.setIndex(labels.get(bc.getAddress()));
            }
        }
    }
}
