import java.util.Scanner;

public class ReportCard {
    private Student[] students;

    private boolean EveryoneWasAsked() {
        for (var student : students) {
            if (!student.getWasAsked())
                return false;
        }
        return true;
    }

    private Student GetRandomStudent() throws Exception {
        if (EveryoneWasAsked())
            throw new Exception("All students have already answered!");

        int index;
        do {
            index = (int) (Math.random() * students.length);
            if (students[index].getWasAsked())
                System.out.println("Student " + students[index].getName() + " has already answered");
            else
                System.out.println("Student " + students[index].getName() + " is answering");
        } while (students[index].getWasAsked());
        return students[index];
    }

    public ReportCard(Student[] students) {
        this.students = students;
    }

    public void PrintHelp() {
        System.out.println("1. /r - choose random student\n" +
                "2. /l - list of student with grades\n" +
                "3. /h - help\n" +
                "4. /e - exit");
    }

    public void AskStudent() {
        Student student;
        try {
            student = GetRandomStudent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Scanner in = new Scanner(System.in);
        String answer;
        do {
            System.out.println("Is student in the seminar? (y/n)");
            answer = in.nextLine();
        } while (!answer.equals("y") && !answer.equals("n"));
        if (answer.equals("y")) {
            student.setInSeminar(true);
        }
        else {
            student.setInSeminar(false);
            return;
        }
        System.out.println("Enter student's mark:");
        try {
            int mark = in.nextInt();
            student.setMark(mark);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (var student : students) {
            if (student.getWasAsked()) {
                list.append(student.toString());
                list.append('\n');
            }
        }
        return list.toString();
    }
}
