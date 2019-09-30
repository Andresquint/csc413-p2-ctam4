package interpreter.bytecode;

public abstract class SymbolicByteCode extends ByteCode {
    private String address;
    private int index = -1;

    /**
     * This method sets symbolic address
     *
     * @param address string of symbolic address
     */
    protected void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns symbolic address
     *
     * @return string of symbolic address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This method sets resolved index
     *
     * @param index resolved index in program ArrayList
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * This method returns resolved index
     *
     * @return resolved index in program ArrayList
     */
    protected int getIndex() {
        if (this.index == -1) {
            throw new IndexOutOfBoundsException();
        }
        return this.index;
    }

    public String toString(VirtualMachine vm) {
        try {
            return this.name + " " + this.getIndex();
        }
        // happens if address is not resolved
        catch (IndexOutOfBoundsException e) {
            return this.name + " " + this.getAddress();
        }
    }
}
