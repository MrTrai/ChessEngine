package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.PieceColor;
import com.chess.engine.board.*;
import com.chess.engine.board.Position;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

    private final static Position OFFSET_UPPER_MOVES_ONE = new Position(-1, 3);
    private final static Position OFFSET_UPPER_MOVES_TWO = new Position(1, 3);

    private final static Position OFFSET_LOWER_MOVES_ONE = new Position(-1, -3);
    private final static Position OFFSET_LOWER_MOVES_TWO = new Position(1, -3);

    private final static Position OFFSET_LEFT_MOVES_ONE = new Position(-3, 1);
    private final static Position OFFSET_LEFT_MOVES_TWO = new Position(-3, -1);

    private final static Position OFFSET_RIGHT_MOVES_ONE = new Position(3, 1);
    private final static Position OFFSET_RIGHT_MOVES_TWO = new Position(3, -1);

    private final static Position[] ALLOWED_MOVES = {
            OFFSET_UPPER_MOVES_ONE,
            OFFSET_UPPER_MOVES_TWO,
            OFFSET_LOWER_MOVES_ONE,
            OFFSET_LOWER_MOVES_TWO,
            OFFSET_LEFT_MOVES_ONE,
            OFFSET_LEFT_MOVES_TWO,
            OFFSET_RIGHT_MOVES_ONE,
            OFFSET_RIGHT_MOVES_TWO
    };

    public Knight(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {

        Position potentialMove = new Position();
        List<Move> legalMoves = new ArrayList<Move>();

        for (Position each : ALLOWED_MOVES) {
            /* check X coordinate for each move is make */
            potentialMove.setX( this.piecePosition.getX() + each.getX() );
            potentialMove.setY( this.piecePosition.getY() + each.getY() );

            if (BoardUtils.isValidCoordinate(potentialMove)) {
                final Tile targetedTile = board.getTile(potentialMove);
                if ( !targetedTile.isOccupied() ) {
                    //Move is still not implemented here
                    legalMoves.add(new MajorMove(board, this, potentialMove));
                } else {
                    //if the tile selected is Occupied
                    final Piece targetedPiece = targetedTile.getPiece();
                    //Check if the piece is enemy or friend
                    final PieceColor targetedPieceColor = targetedPiece.getPieceColor();
                    //Enemy detected
                    if (this.pieceColor != targetedPieceColor) {
                        //Different type of move here for attacking
                        //Attack Move
                        legalMoves.add(new AttackMove(board, this, potentialMove, targetedPiece));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}

