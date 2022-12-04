public abstract class Player {
    protected Color pieceColor;

    protected Player(Color color) {
        pieceColor = color;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public abstract boolean makeMove(Field field);
}
