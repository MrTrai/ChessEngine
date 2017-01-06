package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class MajorMove extends Move {
    public MajorMove(final Board board,
            final Piece selectedPiece,
            final Position selectedPosition) {
        super(board, selectedPiece, selectedPosition);
    }
}
