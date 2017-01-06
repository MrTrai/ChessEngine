package com.chess.engine.pieces;

import java.util.List;

import com.chess.engine.PieceColor;
import com.chess.engine.board.Board;
import com.chess.engine.board.Position;
import com.chess.engine.board.Move;

public abstract class Piece {
    protected final Position piecePosition;
    protected final PieceColor pieceColor; 

    public Piece(Position piecePosition, PieceColor pieceColor) {
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public boolean isWhite() {
        return this.pieceColor == PieceColor.WHITE;
    }

    public boolean isBlack() {
        return this.pieceColor == PieceColor.BLACK;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);
}
