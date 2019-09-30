package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
        int a = 0, b = 0, result = 0;
        // retrieve last two values from runStack
        try {
            b = vm.popRunStack();
            a = vm.popRunStack();
        }
        // happens if stack is empty
        catch (EmptyStackException e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }
        switch (this.operation) {
            case "+":
                result = (int) a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "/":
                result = a / b;
                break;
            case "*":
                result = a * b;
                break;
            case "==":
                result = a == b ? 1 : 0;
                break;
            case "!=":
                result = a != b ? 1 : 0;
                break;
            case "<=":
                result = a <= b ? 1 : 0;
                break;
            case "<":
                result = a < b ? 1 : 0;
                break;
            case ">=":
                result = a >= b ? 1 : 0;
                break;
            case ">":
                result = a > b ? 1 : 0;
                break;
            case "|":
                result = a == 1 || b == 1 ? 1 : 0;
                break;
            case "&":
                result = a == 1 && b == 1 ? 1 : 0;
                break;
        }
        vm.pushRunStack(result);
    }

    public String toString() {
        return this.name + " " + this.operation;
    }
}
