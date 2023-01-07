package Logic;

import Interface.Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class FileManager {
    private final Path root;
    private ArrayList<File> files;
    private boolean hasCycles;

    /**
     * Ищет все файлы в данной файловой системе
     */
    private void findAllFiles() throws IOException {
        files = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(root)) {
            walk.forEach((Path p) -> {
                if (!Files.isDirectory(p)) {
                    files.add(new File(root, p));
                }
            });
        }
    }

    /**
     * Проверяет на наличие циклов
     */
    private void checkCycles() {
        hasCycles = false;
        int[] used = new int[files.size()];
        Arrays.fill(used, 0);
        for (int i = 0; i < files.size(); ++i) {
            if (used[i] == 0) {
                dfs(i, used);
            }
        }
    }

    /**
    обход графа в глубину
     */
    private void dfs(int index, int[] used) {
        used[index] = 1;
        Path[] dependencies = files.get(index).getDependencies();
        for (Path dependency : dependencies) {
            int dependencyIndex = indexOfFile(dependency);
            if (used[dependencyIndex] == 0) {
                dfs(dependencyIndex, used);
            } else if (used[dependencyIndex] == 1) {
                hasCycles = true;
            }
        }
        used[index] = 2;
    }

    /**
     * @param path путь к файлу
     * @return индекс данного файла в списке файлов files или -1, если файл с таким путем не найден
     */
    private int indexOfFile(Path path) {
        for (int i = 0; i < files.size(); ++i) {
            if (path != null && path.equals(files.get(i).getPath())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param root путь к корневой папке
     */
    public FileManager(Path root) throws IOException {
        this.root = root;
        findAllFiles();
    }

    @Override
    public String toString() {
        checkCycles();
        if (hasCycles) {
            throw new IllegalArgumentException("File system has cycles");
        }
        Collections.sort(files);
        StringBuilder result = new StringBuilder();
        for (File file : files) {
            try {
                result.append(file.getText());
            } catch (IOException e) {
                Output.writeErrorMessage(e.getMessage());
            }
        }
        return result.toString();
    }
}
