package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class Move {
    final Board board;
    final Piece selectedPiece;
    final Position piecePosition;

    public Move(final Board board,
            final Piece selectedPiece,
            final Position selectedPosition) {
        this.board = board;
        this.selectedPiece = selectedPiece;
        this.piecePosition = selectedPosition;
    }
}
