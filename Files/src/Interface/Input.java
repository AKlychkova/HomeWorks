package Interface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Input {
    /**
     * Считывает абсолютный путь к корневой папке
     * @return абсолютный путь к корневой папке
     */
    public static Path readRootFolder() {
        Path path;
        do {
            System.out.println("Enter absolute path for root folder:");
            try (Scanner in = new Scanner(System.in)) {
                path = Paths.get(in.nextLine());
            }
        } while (!Files.exists(path) || !path.isAbsolute());
        return path;
    }
}
