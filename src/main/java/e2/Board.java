package e2;

public interface Board {
    void checkOutOfBoard(int row, int col);

    Pair<Integer, Integer> getKnight();

    boolean hasKnight(int row, int col);

    boolean hasPawn(int row, int col);

    boolean hit(int row, int col);
}
