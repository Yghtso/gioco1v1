package scuola.esercitazione.gioco1v1;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(Position other) {
        return row == other.getRow() && column == other.getColumn();
    }

    public Position copy(Position other) {
        return new Position(other.row, other.column);
    }

    public Position clone() {
        return new Position(this.row, this.column);
    }

    public boolean isInBoard() {
        return (this.getRow() >= 1 && this.getRow() <= 8) && (this.getColumn() >= 1 && this.getColumn() <= 8);
    }
}
