/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.nio.file.Path;
import java.util.function.Consumer;


/**
 * Responsible for creating standard terminal.
 */
public class StandardTerminalBuilder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static StandardTerminalBuilder instance;
    private InputTerminal inputTerminal;
    private OutputTerminal outputTerminal;
    private Path workingDirectory;
    private Consumer<String> outputHandler;
    private Consumer<String> outputErrorHandler;



    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private StandardTerminalBuilder() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static StandardTerminalBuilder getInstance() {
        if (instance == null) {
            instance = new StandardTerminalBuilder();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public StandardTerminalBuilder workingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory;

        return this;
    }

    public StandardTerminalBuilder outputHandler(Consumer<String> outputHandler) {
        this.outputHandler = outputHandler;

        return this;
    }

    public StandardTerminalBuilder outputErrorHandler(Consumer<String> outputErrorHandler) {
        this.outputErrorHandler = outputErrorHandler;

        return this;
    }

    public Terminal build() {
        buildOutputTerminal();
        buildInputTerminal();

        return buildTerminal();
    }

    private void buildOutputTerminal() {
        outputTerminal = new StandardOutputTerminal(outputHandler, outputErrorHandler);
    }

    private void buildInputTerminal() {
        inputTerminal = new StandardInputTerminal(workingDirectory, outputTerminal);
    }

    private Terminal buildTerminal() {
        return new Terminal(inputTerminal, outputTerminal);
    }
}
