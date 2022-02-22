/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.util.List;


/**
 * Responsible for handling terminal output.
 */
public interface OutputTerminal {

    /**
     * Receives a message from terminal.
     * 
     * @param       message Terminal message
     */
    void receive(String message);

    /**
     * Receives an error message from terminal.
     * 
     * @param       message Terminal message
     */
    void receiveError(String message);
    
    /**
     * Erases output.
     */
    void clear();
    
    /**
     * Retrieves terminal message history.
     * 
     * @return      Terminal history
     */
    List<String> getHistory();
    
    /**
     * Retrieves terminal error message history.
     * 
     * @return      Terminal history
     */
    List<String> getErrorHistory();
}
