package com.chess.engine.board;

public class BoardUtils {

    public static final int NUM_COLS = 8;
    public static final int NUM_ROWS = 8;
    public static final int NUM_TILES = 64;

    private BoardUtils() {
        throw new RuntimeException("Can't instantiate");
    }

    public static boolean isValidCoordinate(Position piecePosition) {
        return  piecePosition.getX() < 8 && piecePosition.getX() >= 0 
                && piecePosition.getY() < 8 && piecePosition.getY() >= 0;
    }
}
