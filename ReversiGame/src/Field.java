import java.util.ArrayList;

public class Field {
    Color[][] matrix = new Color[8][8];

    // Меняет имя фишки
    void changePieceColor(int x, int y) {
        matrix[x][y] = matrix[x][y].getOpposite();
    }

    public Field() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                matrix[i][j] = Color.EMPTY;
            }
        }
        matrix[3][3] = Color.WHITE;
        matrix[4][4] = Color.WHITE;
        matrix[3][4] = Color.BLACK;
        matrix[4][3] = Color.BLACK;
    }

    // Возвращает значение ячейки
    public Color getColor(int x, int y) {
        return matrix[x][y];
    }

    // Проверяет на заполненность
    public boolean isFilled() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (matrix[i][j] == Color.EMPTY)
                    return false;
            }
        }
        return true;
    }

    // Считает фишки определенного цвета
    public int countPieces(Color color) {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (matrix[i][j] == color)
                    ++count;
            }
        }
        return count;
    }

    // Проверяет ход на корректность
    public boolean checkMove(Piece piece) {
        if (matrix[piece.getRow()][piece.getColumn()] != Color.EMPTY) {
            return false;
        }
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int x = piece.getRow() + i;
                int y = piece.getColumn() + j;
                int countEnemy = 0;
                while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (matrix[x][y] == Color.EMPTY) {
                        break;
                    }
                    // Если встретилась фишка врага - считаем её
                    if (matrix[x][y] == piece.getColor().getOpposite()) {
                        ++countEnemy;
                    }
                    // Если встретилась своя фишка
                    if (matrix[x][y] == piece.getColor()) {
                        if (countEnemy != 0) {
                            return true;
                        } else {
                            break;
                        }
                    }
                    x += i;
                    y += j;
                }
            }
        }
        return false;
    }

    // Ищет возможные ходы
    public ArrayList<Piece> findAvailableMoves(Color color) {
        ArrayList<Piece> moves = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                Piece current = new Piece(i, j, color);
                if (checkMove(current)) {
                    moves.add(current);
                }
            }
        }
        return moves;
    }


    // Добавляет фишку
    public void addPiece(Piece new_piece) {
        if (matrix[new_piece.getRow()][new_piece.getColumn()] != Color.EMPTY) {
            throw new IllegalArgumentException("cell is not empty");
        }
        matrix[new_piece.getRow()][new_piece.getColumn()] = new_piece.getColor();
        for (var p : findAvailableCapture(new_piece)) {
            changePieceColor(p.getRow(), p.getColumn());
        }
    }

    // Ищет фишки, которые будут перекрашены, если добавить piece
    public ArrayList<Piece> findAvailableCapture(Piece piece) {
        ArrayList<Piece> total_enemy = new ArrayList<>();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int x = piece.getRow() + i;
                int y = piece.getColumn() + j;
                ArrayList<Piece> enemy = new ArrayList<>();
                while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (matrix[x][y] == Color.EMPTY) {
                        break;
                    }
                    // Если встретилась фишка врага - собираем её
                    if (matrix[x][y] == piece.getColor().getOpposite()) {
                        enemy.add(new Piece(x, y, matrix[x][y]));
                    }
                    // Если встретилась своя фишка
                    if (matrix[x][y] == piece.getColor()) {
                        total_enemy.addAll(enemy);
                        break;
                    }
                    x += i;
                    y += j;
                }
            }
        }
        // Возвращаем массив с найдеными клетками врага
        return total_enemy;
    }
}
