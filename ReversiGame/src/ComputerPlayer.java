import java.util.ArrayList;

public class ComputerPlayer extends Player {

//    int evaluation() {
//
//    }

    public ComputerPlayer(Color color) {
        super(color);
    }

    @Override
    public boolean makeMove(Field field) {
        ArrayList<Piece> availapleMoves = field.findAvailableMoves(pieceColor);
        if (availapleMoves.size() == 0) {
            return false;
        }
        double maxEvaluation = 0;
        Piece bestMove = null;
        for (var move : availapleMoves) {
            double evaluation;
            if (move.isCorner()) {
                evaluation = 0.8;
            } else if (move.isEdge()) {
                evaluation = 0.4;
            } else {
                evaluation = 0;
            }
            for (var cupturePiece : field.findAvailableCapture(move)) {
                if (cupturePiece.isEdge()) {
                    evaluation += 2;
                } else {
                    evaluation += 1;
                }
            }
            if (evaluation > maxEvaluation) {
                maxEvaluation = evaluation;
                bestMove = move;
            }
        }
        field.addPiece(bestMove);
        return true;
    }
}
