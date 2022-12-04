import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        do {
            new Game(Output.getPlayersAmount()).start();
        } while (Output.playAgain());
    }
}
