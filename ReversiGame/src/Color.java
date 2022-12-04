public enum Color {
    BLACK, EMPTY, WHITE;

    // Возвращает противоположный цвет
    public Color getOpposite() {
        if (this == Color.WHITE) {
            return Color.BLACK;
        } else if (this == Color.BLACK) {
            return Color.WHITE;
        } else {
            return Color.EMPTY;
        }
    }
}
