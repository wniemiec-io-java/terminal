package wniemiec.io.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class MockInputTerminal implements InputTerminal {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<String> input;
    private final OutputTerminal outputTerminal;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    MockInputTerminal(OutputTerminal outputTerminal) {
        this.outputTerminal = outputTerminal;
        input = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void exec(String... commands) throws IOException {
        input.addAll(Arrays.asList(commands));
        outputTerminal.receive("pong");
        outputTerminal.receiveError("p_ng");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInput() {
        return input;
    }
}
