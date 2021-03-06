/**
 * Copyright (c) William Niemiec.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package wniemiec.io.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;


/**
 * Standard manager of terminal input.
 */
class StandardInputTerminal implements InputTerminal {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Runtime runtime;
    private final OutputTerminal outputTerminal;
    private final File workingDirectory;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Standard terminal input manager.
     * 
     * @param       workingDirectory Working directory
     * @param       outputTerminal Output manager
     * 
     * @throws      IllegalArgumentException If outputTerminal is null
     */
    StandardInputTerminal(Path workingDirectory, OutputTerminal outputTerminal) {
        if (outputTerminal == null) {
            throw new IllegalArgumentException("Output terminal cannot be null");
        }

        this.workingDirectory = (workingDirectory == null) ? null : workingDirectory.toFile();
        this.outputTerminal = outputTerminal;
        runtime = Runtime.getRuntime();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void exec(String... commands) throws IOException {
        Process process = runTerminal(commands);
     
        readOutput(process);
        readErrorOutput(process); 
    }

    private Process runTerminal(String... commands) throws IOException {
        return runtime.exec(
            commands, 
            null, 
            workingDirectory
        );
    }

    private void readOutput(Process process) throws IOException {
        try (BufferedReader buffer = buildInputStream(process.getInputStream())) {
            for (String line = ""; line != null; line = buffer.readLine()) {
                outputTerminal.receive(line);
            }
        }
    }

    private BufferedReader buildInputStream(InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream));
    }

    private void readErrorOutput(Process process) throws IOException {
        try (BufferedReader buffer = buildInputStream(process.getErrorStream())) {
            for (String line = ""; line != null; line = buffer.readLine()) {
                outputTerminal.receiveError(line);
            }
        }
    }
}
