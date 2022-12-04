public class Piece {
    int row;
    int column;
    Color color;

    public Piece(int row, int column, Color color) {
        setRow(row);
        setColumn(column);
        setColor(color);
    }

    public void setRow(int row) {
        if (row >= 0 && row < 8)
            this.row = row;
        else
            throw new IllegalArgumentException("the coordinates should be from 0 to 7");
    }

    public void setColumn(int column) {
        if (column >= 0 && column < 8)
            this.column = column;
        else
            throw new IllegalArgumentException("the coordinates should be from 0 to 7");
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Color getColor() {
        return color;
    }

    // Проверяет является ли угловой
    public boolean isCorner() {
        return (row == 0 || row == 8) && (column == 0 || column == 8);
    }

    // проверяет является ли кромочной
    public boolean isEdge() {
        return row == 0 || row == 8 || column == 0 || column == 8;
    }
}
