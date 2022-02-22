package wniemiec.io.java;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TerminalTest {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private MockInputTerminal mockInputTerminal;
    private MockOutputTerminal mockOutputTerminal;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUpMocks() {
        mockOutputTerminal = new MockOutputTerminal();
        mockInputTerminal = new MockInputTerminal(mockOutputTerminal);
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testInput() throws IOException {
        Terminal terminal = new Terminal(mockInputTerminal, mockOutputTerminal);

        terminal.exec("ping");

        Assertions.assertEquals(List.of("ping"), mockInputTerminal.getInput());
    }

    @Test
    void testOutput() throws IOException {
        Terminal terminal = new Terminal(mockInputTerminal, mockOutputTerminal);

        terminal.exec("ping");

        Assertions.assertEquals(List.of("pong"), mockOutputTerminal.getHistory());
    }

    @Test
    void testErrorOutput() throws IOException {
        Terminal terminal = new Terminal(mockInputTerminal, mockOutputTerminal);

        terminal.exec("ping");

        Assertions.assertEquals(List.of("p_ng"), mockOutputTerminal.getErrorHistory());
    }
}
