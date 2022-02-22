/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.util.List;
import java.util.function.Consumer;

/**
 * Standard manager of terminal output.
 */
class StandardOutputTerminal implements OutputTerminal {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Consumer<String> NULL_HANDLER;
    private Consumer<String> outputHandler;
    private Consumer<String> outputErrorHandler;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        NULL_HANDLER = message -> {};
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Standard terminal output manager.
     * 
     * @param       outputHandler
     * @param       outputErrorHandler
     */
    public StandardOutputTerminal(Consumer<String> outputHandler, Consumer<String> outputErrorHandler) {
        this.outputHandler = (outputHandler == null) ? NULL_HANDLER : outputHandler;
        this.outputErrorHandler = (outputErrorHandler == null) ? NULL_HANDLER : outputErrorHandler;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void receive(String line) {
        outputHandler.accept(line);
    }

    @Override
    public void receiveError(String line) {
        outputErrorHandler.accept(line);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    @Override
    public List<String> getHistory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getErrorHistory() {
        // TODO Auto-generated method stub
        return null;
    }
}
