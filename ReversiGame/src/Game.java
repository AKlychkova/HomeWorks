public class Game {
    Field field;
    Player player1;
    Player player2;
    int record;

    boolean isGameEnded() {
        if (field.isFilled()) {
            return true;
        }
        if (field.countPieces(Color.BLACK) == 0 || field.countPieces(Color.WHITE) == 0) {
            return true;
        }
        return false;
    }

    void checkRecord() {
        if (player1 instanceof HumanPlayer && field.countPieces(player1.getPieceColor()) > record) {
            record = field.countPieces(player1.getPieceColor());
        }
        if (player2 instanceof HumanPlayer && field.countPieces(player2.getPieceColor()) > record) {
            record = field.countPieces(player2.getPieceColor());
        }
    }

    public Game(int playersAmount) {
        record = 0;
        field = new Field();
        player1 = new HumanPlayer(Color.BLACK);
        if (playersAmount == 1) {
            player2 = new ComputerPlayer(Color.WHITE);
        } else {
            player2 = new HumanPlayer(Color.WHITE);
        }
    }

    public void start() {
        Output.welcome();
        Output.displayField(field, field.findAvailableMoves(player1.getPieceColor()));
        int currentPlayer = 1;
        int countSkipMoves = 0;
        do {
            if (currentPlayer == 1) {
                Output.displayMove(1);
                if (!player1.makeMove(field)) {
                    Output.displaySkipMove();
                    ++countSkipMoves;
                    currentPlayer *= (-1);
                    continue;
                }
                Output.displayField(field, field.findAvailableMoves(player2.getPieceColor()));
            } else {
                Output.displayMove(2);
                if (!player2.makeMove(field)) {
                    Output.displaySkipMove();
                    ++countSkipMoves;
                    currentPlayer *= (-1);
                    continue;
                }
                Output.displayField(field, field.findAvailableMoves(player1.getPieceColor()));
            }
            countSkipMoves = 0;
            currentPlayer *= (-1);
        } while (!isGameEnded() || countSkipMoves > 1);
        checkRecord();
        Output.displayResalt(field.countPieces(player1.getPieceColor()), field.countPieces(player2.getPieceColor()), record);
    }
}
