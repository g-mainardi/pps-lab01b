package e2;

import java.util.Random;

public class ChessBoard implements Board {
    private final int size;
    private final Pair<Integer,Integer> pawn;
    private Pair<Integer,Integer> knight;
    private final Random random = new Random();

    public ChessBoard(int size) {
        this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();
    }

    public ChessBoard(int size, Pair<Integer, Integer> pawn, Pair<Integer, Integer> knight) {
        this.size = size;
        this.pawn = pawn;
        this.knight = knight;
    }

    public Pair<Integer, Integer> getKnight() {
        return knight;
    }

    private Pair<Integer,Integer> randomEmptyPosition(){
        Pair<Integer,Integer> pos = new Pair<>(this.randomCoord(), this.randomCoord());
        return isPositionOccupied(pos) ? randomEmptyPosition() : pos;
    }

    private boolean isPositionOccupied(Pair<Integer, Integer> pos) {
        return this.pawn != null && this.pawn.equals(pos);
    }

    private int randomCoord() {
        return this.random.nextInt(this.size);
    }

    private void setKnight(final int row, final int col) {
        this.knight = new Pair<>(row, col);
    }

    @Override
    public void checkOutOfBoard(int row, int col) {
        if (row < 0 || col < 0 || row >= this.size || col >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean hasKnight(int row, int col) {
        return this.knight.equals(new Pair<>(row, col));
    }

    @Override
    public boolean hasPawn(int row, int col) {
        return this.pawn.equals(new Pair<>(row, col));
    }

    @Override
    public boolean hit(int row, int col) {
        this.setKnight(row, col);
        return this.pawn.equals(this.knight);
    }
}
