public abstract class Player {
    // Цвет, за который играет игрок
    protected Color pieceColor;

    protected Player(Color color) {
        pieceColor = color;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    // Ход
    public abstract boolean makeMove(Field field);
}
