package e2;
import org.junit.jupiter.api.*;

import java.util.Optional;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  public static final int SIZE = 5;
  public static final Pair<Integer, Integer> KNIGHT = new Pair<>(2,2);
  public static final Pair<Integer, Integer> KNIGHT_NO_HIT = new Pair<>(3,0);
  public static final Pair<Integer, Integer> PAWN = new Pair<>(3,4);
  public static final int N_KNIGHTS = 1;
  public static final int N_PAWNS = 1;
  Logics logics;

  public void init() {
    logics = new LogicsImpl(new ChessBoard(SIZE, PAWN, KNIGHT), new KnightRules());
  }

  public void initRandom() {
    logics = new LogicsImpl(new ChessBoard(SIZE), new KnightRules());
  }

  @Test
  public void testKnight() {
    this.init();
    testPosition(KNIGHT, getKnightPredicate());
  }

  private BiPredicate<Integer, Integer> getKnightPredicate() {
    return (Integer x, Integer y) -> logics.hasKnight(x, y);
  }

  @Test
  public void testPawn() {
    this.init();
    testPosition(PAWN, getPawnPredicate());
  }

  private BiPredicate<Integer, Integer> getPawnPredicate() {
    return (Integer x, Integer y) -> logics.hasPawn(x, y);
  }

  public void testPosition(Pair<Integer, Integer> pos, BiPredicate<Integer, Integer> hasPos) {
    for(int i=0; i<SIZE; i++) {
      for(int j=0; j<SIZE; j++) {
        if(i==pos.getX() && j==pos.getY()) {
          assertTrue(hasPos.test(i,j));
          continue;
        }
        assertFalse(hasPos.test(i,j));
      }
    }
  }

  @Test
  public void testHitOutOfBounds() {
    this.init();
    assertThrows(
            IndexOutOfBoundsException.class,
            () -> logics.hit(-SIZE, -SIZE)
    );
    assertThrows(
            IndexOutOfBoundsException.class,
            () -> logics.hit(SIZE, SIZE)
    );
  }

  @Test
  public void testNoHitMovement(){
    this.init();
    assertFalse(logics.hit(KNIGHT_NO_HIT.getX(), KNIGHT_NO_HIT.getY()));
    assertTrue(logics.hasKnight(KNIGHT_NO_HIT.getX(), KNIGHT_NO_HIT.getY()));
  }

  @Test
  public void testHitMovement(){
    this.init();
    assertTrue(logics.hit(PAWN.getX(), PAWN.getY()));
  }

  // Test for Initial Random Position
  @Test
  public void testPawnIsPresent() {
    this.initRandom();
    Optional<Pair<Integer, Integer>> pawnPos = this.searchWithCriteria(getPawnPredicate());
    assertTrue(pawnPos.isPresent());
  }

  @Test
  public void testKnightIsPresent() {
    this.initRandom();
    Optional<Pair<Integer, Integer>> knightPos = this.searchWithCriteria(getKnightPredicate());
    assertTrue(knightPos.isPresent());
  }

  @Test
  public void testSingleRandomGeneration() {
    this.initRandom();
    int knightCounter = 0;
    int pawnCounter = 0;
    for(int i=0; i<SIZE; i++) {
      for(int j=0; j<SIZE; j++) {
        if(logics.hasKnight(i,j)){
          knightCounter++;
        }
        if(logics.hasPawn(i,j)){
          pawnCounter++;
        }
      }
    }
    assertEquals(N_KNIGHTS, knightCounter);
    assertEquals(N_PAWNS, pawnCounter);
  }

  private Optional<Pair<Integer, Integer>> searchWithCriteria(BiPredicate<Integer, Integer> hasPos) {
    for(int i=0; i<SIZE; i++) {
      for(int j=0; j<SIZE; j++) {
        if(hasPos.test(i,j)){
          return Optional.of(new Pair<>(i,j));
        }
      }
    }
    return Optional.empty();
  }
}
