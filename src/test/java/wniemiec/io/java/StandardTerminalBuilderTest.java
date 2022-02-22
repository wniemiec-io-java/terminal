package wniemiec.io.java;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StandardTerminalBuilderTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String obtainedOutputMessage;
    private String obtainedOutputErrorMessage;
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testUniversalTerminalCommand() throws IOException {
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .build();

        runUniversalCommand(terminal);

        Assertions.assertEquals(1, terminal.getErrorHistory().size());
    }

    private void runUniversalCommand(Terminal terminal) throws IOException {
        // Available in Windows, Ubuntu and MacOS
        terminal.exec("echo", "hello");
    }

    @Test
    void testOutputHandler() throws IOException {
        
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(message -> { obtainedOutputMessage = message; })
            .build();

        runUniversalCommand(terminal);

        Assertions.assertEquals("hello", obtainedOutputMessage);
    }

    @Test
    void testOutputErrorHandler() throws IOException {
        
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .outputErrorHandler(message -> { obtainedOutputErrorMessage = message; })
            .build();

        runUniversalCommand(terminal);

        Assertions.assertEquals("", obtainedOutputErrorMessage);
    }

    @Test
    void testOutputHandlers() throws IOException {
        
        Terminal terminal = StandardTerminalBuilder
            .getInstance()
            .outputHandler(message -> { obtainedOutputMessage = message; })
            .outputErrorHandler(message -> { obtainedOutputErrorMessage = message; })
            .build();

        runUniversalCommand(terminal);

        Assertions.assertEquals("hello", obtainedOutputMessage);
        Assertions.assertEquals("", obtainedOutputErrorMessage);
    }
}
