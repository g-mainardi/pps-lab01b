package e2;

public class LogicsImpl implements Logics {

	private final Rules knightRules;
	private final Board board;

	public LogicsImpl(ChessBoard chessBoard, KnightRules knightRules){
		this.board = chessBoard;
		this.knightRules = knightRules;
    }

	@Override
	public boolean hit(int row, int col) {
		this.board.checkOutOfBoard(row, col);
		return this.isLegalAndHit(row, col);
	}

	private boolean isLegalAndHit(int row, int col) {
		return this.knightRules.isLegalMove(row, col, this.board.getKnight()) && this.board.hit(row, col);
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.board.hasKnight(row, col);
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.board.hasPawn(row, col);
	}
}
