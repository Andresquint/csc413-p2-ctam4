package interpreter.bytecode;



import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BopCode extends ByteCode {
    private static Set<String> operators;
    private String operation;

    public void init(ArrayList args) {
        this.name = "BOP";
        // set operators
        operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("/");
        operators.add("*");
        operators.add("==");
        operators.add("!=");
        operators.add("<=");
        operators.add("<");
        operators.add(">=");
        operators.add(">");
        operators.add("|");
        operators.add("&");
        // check if there is only one argument
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        // check if argument is one of operators
        if (!operators.contains((String) args.get(0))) {
            throw new IllegalArgumentException();
        }
        this.operation = (String) args.get(0);
    }

    public void execute(VirtualMachine vm) {
        // TODO
    }

    public String toString() {
        return this.name + " " + this.operation;
    }
}
