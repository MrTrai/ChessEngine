package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class AttackMove extends Move {

    final Piece targetedPiece;

    public AttackMove(final Board board,
            final Piece selectedPiece,
            final Position selectedPosition,
            final Piece targetedPiece) {
        super(board, selectedPiece, selectedPosition);
        this.targetedPiece = targetedPiece;
    }

}