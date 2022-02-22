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
    private InputTerminal inputTerminal;
    private OutputTerminal outputTerminal;
    private Path workingDirectory;
    private Consumer<String> outputHandler;
    private Consumer<String> outputErrorHandler;
    

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

    public StandardTerminalBuilder inputTerminal(InputTerminal inputTerminal) {
        this.inputTerminal = inputTerminal;

        return this;
    }

    public StandardTerminalBuilder outputTerminal(OutputTerminal outputTerminal) {
        this.outputTerminal = outputTerminal;

        return this;
    }

    public Terminal build() {
        buildOutputTerminal();
        buildInputTerminal();

        return buildTerminal();
    }

    private void buildOutputTerminal() {
        if (outputTerminal != null) {
            return;
        }

        outputTerminal = new StandardOutputTerminal(outputHandler, outputErrorHandler);
    }

    private void buildInputTerminal() {
        if (inputTerminal != null) {
            return;
        }

        inputTerminal = new StandardInputTerminal(workingDirectory, outputTerminal);
    }

    private Terminal buildTerminal() {
        return new Terminal(inputTerminal, outputTerminal);
    }
}
