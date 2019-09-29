package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import interpreter.bytecode.ByteCode;

public class ByteCodeLoader extends Object {
    private BufferedReader byteSource;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        String line;
        ArrayList<String> token;

        // create program object
        Program program = new Program();

        try {
            // start reading line by line
            while ((line = this.byteSource.readLine()) != null) {
                // split is used here because StringTokenizer is a legacy class
                // ref: https://docs.oracle.com/javase/7/docs/api/java/util/StringTokenizer.html
                token = new ArrayList<String>(Arrays.asList(line.split(" ")));

                // remove empty elements
                token.removeAll(Arrays.asList("", null));

                // if token has more than one list item
                if (token.size() > 0) {
                    // build ByteCode instance
                    Class<?> c = Class.forName("interpreter.bytecode." + CodeTable.getClassName(token.get(0)));
                    ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();

                    // if there are arguments
                    if (token.size() > 1) {
                        // remove ByteCode name
                        token.remove(0);

                        // initialize ByteCode instance with token
                        bc.init(token);
                    }
                    // if there is no arguments
                    else {
                        // initialize ByteCode instance with null
                        bc.init(null);
                    }

                    // put instance into program
                    program.addCode(bc);
                }
            }

            // resolve symbolic addresses
            program.resolveAddress();
        }
        // happens if there is any exception
        catch (Exception e) {
            System.out.println("**** " + e);
            System.exit(-1);
        }

        // return program object
        return program;
    }
}
