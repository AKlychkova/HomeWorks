package Logic;

import Interface.Output;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class File implements Comparable<File> {
    private final Path path;
    private final Path root;
    private final ArrayList<Path> dependencies;

    /**
     * ищет директивы в тексте файла и составляет список зависимостей
     * @throws IOException если файла не существует или он не читаемый
     */
    private void findDependencies() throws IOException {
        if (Files.exists(path) && Files.isReadable(path)) {
            try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
                stream.forEach((String line) -> {
                    if (line.contains("require")) {
                        Path dependence = Paths.get(root.toString(), line.substring(9, line.length() - 1) + ".txt");
                        if (Files.exists(dependence)) {
                            dependencies.add(dependence);
                        }
                    }
                });
            }
        } else {
            throw new IOException("Cannot read file: " + path);
        }
    }

    /**
     * @return путь к файлу
     */
    public Path getPath() {
        return Paths.get(path.toString());
    }

    /**
     * @return массив путей к файлам, от которых зависит данный
     */
    public Path[] getDependencies() {
        Path[] dependenciesArray = new Path[dependencies.size()];
        dependencies.toArray(dependenciesArray);
        return dependenciesArray;
    }

    /**
     * @param root путь к корневому файлу, данной файловой системы
     * @param path путь к файлу
     */
    public File(Path root, Path path) {
        if (Files.exists(root)) {
            this.root = root;
        } else {
            throw new IllegalArgumentException(root + "does not exist.");
        }

        if (Files.exists(path)) {
            if (!Files.isDirectory(path)) {
                this.path = path;
            } else {
                throw new IllegalArgumentException("Cannot create File with directory path.");
            }
        } else {
            throw new IllegalArgumentException(path + "does not exist.");
        }
        dependencies = new ArrayList<>();
        try {
            findDependencies();
        } catch (IOException e) {
            Output.writeErrorMessage(e.getMessage());
        }
    }

    /**
     * @return текст файла
     * @throws IOException если файл не существует или не читаемый
     */
    public String getText() throws IOException {
        StringBuilder text = new StringBuilder();
        if (Files.exists(path) && Files.isReadable(path)) {
            try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
                stream.forEach((String line) -> text.append(line).append("\n"));
            }
        } else {
            throw new IOException("Cannot read file: " + path);
        }
        return text.toString();
    }

    /**
     * Проверяет зависит ли данный файл от другого
     * @param other другой файл
     * @return true, если файл зависит от other
     */
    public boolean dependOn(File other) {
        if (dependencies == null) {
            return false;
        } else {
            return dependencies.contains(other.path);
        }
    }

    @Override
    public int compareTo(File other) {
        if (other.dependOn(this)) {
            return -1;
        } else if (this.dependOn(other)) {
            return 1;
        } else {
            return 0;
        }
    }
}
