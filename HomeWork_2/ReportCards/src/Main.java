import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Student[] students = { new Student("Ivan Ivanov"), new Student("Petr Petrov"),
                new Student("Anastasiya Klychkova"), new Student("Vasiliy Pupkin")};
        var card = new ReportCard(students);
        card.PrintHelp();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("/e")) {
            switch (input) {
                case "/r":
                    card.AskStudent();
                    break;
                case "/l":
                    System.out.println(card);
                    break;
                case "/h":
                    card.PrintHelp();
                    break;
                default:
                    System.out.println("Incorrect command!");
                    break;
            }
            input = in.nextLine();
        }
    }
}