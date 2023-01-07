package Interface;

import Logic.FileManager;

public class Output {
    /**
     * Выводит результат работы программы - конкотенацию содержимого файлов в соответствии с их зависимомтями
     * @param manager файловая система, для которой необходимо вывести результат
     */
    public static void writeResult(FileManager manager) {
        try {
            System.out.println(manager);
        } catch (IllegalArgumentException e) {
            writeErrorMessage(e.getMessage());
        }
    }

    /**
     * Выводит сообщение об ошибке
     * @param message сообщение, которое необходимо вывести
     */
    public static void writeErrorMessage(String message) {
        System.out.println("!!!" + message);
    }
}
