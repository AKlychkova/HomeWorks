import java.util.ArrayList;
import java.util.Scanner;

public class Output {
    static Scanner in = new Scanner(System.in);

    public static void welcome() {
        System.out.println("~~~~~~ WELCOME TO REVERSI ~~~~~~");
        System.out.println("Правила:\n" +
                "\t1. при очередном ходе фишку можно ставить на свободную клетку в любом" +
                "направлении, но обязательно рядом хотя бы с одной из фишек противника;\n" +
                "\t2. фишка должна ставиться так, чтобы хотя бы одна из фишек противника" +
                "оказалась замкнутой своими фишками. При этом замкнутые фишки противника" +
                "меняют цвет и становятся своими;\n" +
                "\t3. фишки могут неоднократно менять цвет, но не могут переставляться на" +
                "доске;\n" +
                "Ваша цель: чтобы к моменту окончания игры число ваших фишек преобладало над числом фишек противника.");
        System.out.println("X обозначаются доступные ходы, \u25CB - фишки 1 игрока, \u25CF - фишки 2 игрока.");
    }

    public static int getPlayersAmount() {
        int amount = 0;

        while (amount != 1 && amount != 2) {
            System.out.println("Введите число игроков (1/2):");
            if (in.hasNextInt()) {
                amount = in.nextInt();
            } else {
                in.next();
            }
        }
        if (amount == 1) {
            System.out.println("Вы будете играть с компьтером на уровне \"начинающий\".");
        }
        return amount;
    }

    public static void displayField(Field field, ArrayList<Piece> availableMoves) {
        System.out.println("_|1_2_3_4_5_6_7_8");
        for (int i = 0; i < 8; ++i) {
            StringBuilder row = new StringBuilder((i + 1) + "|");
            for (int j = 0; j < 8; ++j) {
                if (field.getColor(i, j) == Color.EMPTY) {
                    boolean isAvailableMove = false;
                    for (var piece : availableMoves) {
                        if (piece.getRow() == i && piece.getColumn() == j) {
                            isAvailableMove = true;
                            break;
                        }
                    }
                    row.append(isAvailableMove ? "X " : ". ");
                } else if (field.getColor(i, j) == Color.BLACK) {
                    row.append("\u25CB ");
                } else if (field.getColor(i, j) == Color.WHITE) {
                    row.append("\u25CF ");
                }
            }
            System.out.println(row);
        }
    }

    public static void displayMove(int playerNumber) {
        System.out.println("Ходит игрок " + playerNumber);
    }

    public static void displaySkipMove() {
        System.out.println("Нет возможных ходов, придется пропустить");
    }

    public static void displayResalt(int player1Points, int player2Points, int record) {
        System.out.printf("Счёт " + player1Points + " : " + player2Points + "\n");
        if (player1Points > player2Points) {
            System.out.println("Победил первый игрок!");
        } else if (player1Points < player2Points) {
            System.out.println("Победил второй игрок!");
        } else {
            System.out.println("Ничья!");
        }
        System.out.println("Рекорд: " + record);
    }

    public static Piece getMove(ArrayList<Piece> availableMoves) {
        System.out.println("Возможные ходы:");
        for (int i = 0; i < availableMoves.size(); ++i) {
            System.out.println("\t" + (i + 1) + ". (" + (availableMoves.get(i).row + 1) + ", "
                    + (availableMoves.get(i).column + 1) + ")");
        }
        int code = 0;
        while (code < 1 || code > availableMoves.size()) {
            System.out.println("Введите номер, соответствующий выбраному ходу:");
            if (in.hasNextInt()) {
                code = in.nextInt();
            } else {
                in.next();
            }
        }
        return availableMoves.get(code - 1);
    }

    public static boolean playAgain() {
        System.out.println("Введите 1, чтобы сыграть еще раз");
        if (in.hasNextInt()) {
            if (in.nextInt() == 1)
                return true;
        }
        return false;
    }
}
