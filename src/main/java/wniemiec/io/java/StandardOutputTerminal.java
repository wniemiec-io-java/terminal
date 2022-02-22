/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.util.ArrayList;
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
    private final Consumer<String> outputHandler;
    private final Consumer<String> outputErrorHandler;
    private final List<String> history;
    private final List<String> errorHistory;


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
    public StandardOutputTerminal(
        Consumer<String> outputHandler, 
        Consumer<String> outputErrorHandler
    ) {
        this.outputHandler = parseHandler(outputHandler);
        this.outputErrorHandler = parseHandler(outputErrorHandler);
        history = new ArrayList<>();
        errorHistory = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private Consumer<String> parseHandler(Consumer<String> handler) {
        if (handler == null) {
            return NULL_HANDLER;
        }

        return handler;
    }

    @Override
    public void receive(String message) {
        outputHandler.accept(message);
        history.add(message);
    }

    @Override
    public void receiveError(String message) {
        outputErrorHandler.accept(message);
        errorHistory.add(message);
    }

    @Override
    public void clear() {
        history.clear();
        errorHistory.clear();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public List<String> getErrorHistory() {
        return errorHistory;
    }
}
