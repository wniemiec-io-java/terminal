/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.io.IOException;


/**
 * Responsible for handling terminal input.
 */
public interface InputTerminal {

    /**
     * Run commands in terminal.
     * 
     * @param       commands Terminal commands
     * 
     * @throws      IOException If terminal cannot be executed
     */
    void exec(String... commands) throws IOException;
}
