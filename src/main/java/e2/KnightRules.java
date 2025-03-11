package e2;

public class KnightRules implements Rules {

    public static final int MAX_JUMPS = 3;

    @Override
    public boolean isLegalMove(int row, int col, Pair<Integer, Integer> pawn) {
        int rowDifference = Math.abs(row - pawn.getX());
        int columnsDifference = Math.abs(col - pawn.getY());
        return rowDifference != 0 && columnsDifference != 0 && (rowDifference + columnsDifference) == MAX_JUMPS;
    }
}
