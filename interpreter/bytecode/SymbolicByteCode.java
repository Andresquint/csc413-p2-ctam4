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
    private int getIndex() {
        return this.index;
    }

    public String toString() {
        // if address is not resolved
        if (getIndex() == -1) {
            return this.name + " " + this.getAddress();
        }
        // if address is resolved
        else {
            return this.name + " " + this.getIndex();
        }
    }
}
