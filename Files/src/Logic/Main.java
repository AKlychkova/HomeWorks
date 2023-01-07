package Logic;

import Interface.Input;
import Interface.Output;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileManager manager;
        try {
            manager = new FileManager(Input.readRootFolder());
        } catch (IOException | SecurityException e) {
            Output.writeErrorMessage(e.getMessage());
            return;
        }
        Output.writeResult(manager);
    }
}