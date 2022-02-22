package wniemiec.io.java;

import java.util.ArrayList;
import java.util.List;


class MockOutputTerminal implements OutputTerminal {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<String> output;
    private final List<String> errorOutput;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    MockOutputTerminal() {
        output = new ArrayList<>();
        errorOutput = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void receive(String message) {
        output.add(message);
    }

    @Override
    public void receiveError(String message) {
        errorOutput.add(message);

    }

    @Override
    public void clear() {
        output.clear();
        errorOutput.clear();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    @Override
    public List<String> getHistory() {
        return output;
    }

    @Override
    public List<String> getErrorHistory() {
        return errorOutput;
    }
}
