import java.util.ArrayList;

public class HumanPlayer extends Player {
    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public boolean makeMove(Field field) {
        ArrayList<Piece> availableMoves = field.findAvailableMoves(pieceColor);
        if (availableMoves.size() == 0) {
            return false;
        }
        Piece move = Output.getMove(availableMoves);
        field.addPiece(move);
        return true;
    }
}
