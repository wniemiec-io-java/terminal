/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.io.IOException;
import java.util.List;


/**
 * Responsible for managing terminal.
 */
public class Terminal {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final InputTerminal inputTerminal;
    private final OutputTerminal outputTerminal;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Terminal manager.
     * 
     * @param       inputTerminal Input manager
     * @param       outputTerminal Output manager
     * 
     * @throws      IllegalArgumentException If any argument is null
     */
    public Terminal(InputTerminal inputTerminal, OutputTerminal outputTerminal) {
        validateConstructorArgs(inputTerminal, outputTerminal);

        this.inputTerminal = inputTerminal;
        this.outputTerminal = outputTerminal;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void validateConstructorArgs(InputTerminal input, OutputTerminal output) {
        if (input == null) {
            throw new IllegalArgumentException("Input terminal cannot be null");
        }

        if (output == null) {
            throw new IllegalArgumentException("Output terminal cannot be null");
        }
    }

    /**
     * Run commands in terminal.
     * 
     * @param       commands Terminal commands
     * 
     * @throws      IOException If terminal cannot be executed
     */
    public void exec(String... commands) throws IOException {
        if (thereAreNoCommands(commands)) {
            return;
        }

        inputTerminal.exec(commands);
    }

    private boolean thereAreNoCommands(String... commands) {
        return  (commands == null) 
                || (commands.length == 0);
    }

    /**
     * Erases output.
     */
    public void clean() {
        outputTerminal.clear();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getHistory() {
        return outputTerminal.getHistory();
    }

    public List<String> getErrorHistory() {
        return outputTerminal.getErrorHistory();
    }
}
