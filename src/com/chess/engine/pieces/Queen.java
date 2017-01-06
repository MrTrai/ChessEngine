package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.PieceColor;
import com.chess.engine.board.*;
import com.chess.engine.board.Position;
import com.google.common.collect.ImmutableList;

public class Queen extends Piece {
    //Rook
    public final static Position OFFSET_HORIZONTAL_RIGHT_1 = new Position(1, 0);
    public final static Position OFFSET_HORIZONTAL_RIGHT_2 = new Position(2, 0);
    public final static Position OFFSET_HORIZONTAL_RIGHT_3 = new Position(3, 0);
    public final static Position OFFSET_HORIZONTAL_RIGHT_4 = new Position(4, 0);
    public final static Position OFFSET_HORIZONTAL_RIGHT_5 = new Position(5, 0);
    public final static Position OFFSET_HORIZONTAL_RIGHT_6 = new Position(6, 0);
    public final static Position OFFSET_HORIZONTAL_RIGHT_7 = new Position(7, 0);

    public final static Position OFFSET_HORIZONTAL_LEFT_1 = new Position(-1, 0);
    public final static Position OFFSET_HORIZONTAL_LEFT_2 = new Position(-2, 0);
    public final static Position OFFSET_HORIZONTAL_LEFT_3 = new Position(-3, 0);
    public final static Position OFFSET_HORIZONTAL_LEFT_4 = new Position(-4, 0);
    public final static Position OFFSET_HORIZONTAL_LEFT_5 = new Position(-5, 0);
    public final static Position OFFSET_HORIZONTAL_LEFT_6 = new Position(-6, 0);
    public final static Position OFFSET_HORIZONTAL_LEFT_7 = new Position(-7, 0);

    public final static Position OFFSET_VERTICAL_UP_1 = new Position(0, 1);
    public final static Position OFFSET_VERTICAL_UP_2 = new Position(0, 2);
    public final static Position OFFSET_VERTICAL_UP_3 = new Position(0, 3);
    public final static Position OFFSET_VERTICAL_UP_4 = new Position(0, 4);
    public final static Position OFFSET_VERTICAL_UP_5 = new Position(0, 5);
    public final static Position OFFSET_VERTICAL_UP_6 = new Position(0, 6);
    public final static Position OFFSET_VERTICAL_UP_7 = new Position(0, 7);

    public final static Position OFFSET_VERTICAL_DOWN_1 = new Position(0, -1);
    public final static Position OFFSET_VERTICAL_DOWN_2 = new Position(0, -2);
    public final static Position OFFSET_VERTICAL_DOWN_3 = new Position(0, -3);
    public final static Position OFFSET_VERTICAL_DOWN_4 = new Position(0, -4);
    public final static Position OFFSET_VERTICAL_DOWN_5 = new Position(0, -5);
    public final static Position OFFSET_VERTICAL_DOWN_6 = new Position(0, -6);
    public final static Position OFFSET_VERTICAL_DOWN_7 = new Position(0, -7);

    //Bishop
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_1 = new Position(1, 1);
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_2 = new Position(2, 2);
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_3 = new Position(3, 3);
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_4 = new Position(4, 4);
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_5 = new Position(5, 5);
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_6 = new Position(6, 6);
    public final static Position OFFSET_DIAGONAL_UP_RIGHT_7 = new Position(7, 7);

    public final static Position OFFSET_DIAGONAL_UP_LEFT_1 = new Position(-1, 1);
    public final static Position OFFSET_DIAGONAL_UP_LEFT_2 = new Position(-2, 2);
    public final static Position OFFSET_DIAGONAL_UP_LEFT_3 = new Position(-3, 3);
    public final static Position OFFSET_DIAGONAL_UP_LEFT_4 = new Position(-4, 4);
    public final static Position OFFSET_DIAGONAL_UP_LEFT_5 = new Position(-5, 5);
    public final static Position OFFSET_DIAGONAL_UP_LEFT_6 = new Position(-6, 6);
    public final static Position OFFSET_DIAGONAL_UP_LEFT_7 = new Position(-7, 7);

    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_1 = new Position(-1, -1);
    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_2 = new Position(-2, -2);
    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_3 = new Position(-3, -3);
    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_4 = new Position(-4, -4);
    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_5 = new Position(-5, -5);
    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_6 = new Position(-6, -6);
    public final static Position OFFSET_DIAGONAL_DOWN_LEFT_7 = new Position(-7, -7);

    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_1 = new Position(1, -1);
    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_2 = new Position(2, -2);
    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_3 = new Position(3, -3);
    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_4 = new Position(4, -4);
    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_5 = new Position(5, -5);
    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_6 = new Position(6, -6);
    public final static Position OFFSET_DIAGONAL_DOWN_RIGHT_7 = new Position(7, -7);

    public final static Position[] ALLOWED_MOVES = {
        //Rook
        OFFSET_HORIZONTAL_RIGHT_1,
        OFFSET_HORIZONTAL_RIGHT_2,
        OFFSET_HORIZONTAL_RIGHT_3,
        OFFSET_HORIZONTAL_RIGHT_4,
        OFFSET_HORIZONTAL_RIGHT_5,
        OFFSET_HORIZONTAL_RIGHT_6,
        OFFSET_HORIZONTAL_RIGHT_7,

        OFFSET_HORIZONTAL_LEFT_1,
        OFFSET_HORIZONTAL_LEFT_2,
        OFFSET_HORIZONTAL_LEFT_3,
        OFFSET_HORIZONTAL_LEFT_4,
        OFFSET_HORIZONTAL_LEFT_5,
        OFFSET_HORIZONTAL_LEFT_6,
        OFFSET_HORIZONTAL_LEFT_7,

        OFFSET_VERTICAL_UP_1,
        OFFSET_VERTICAL_UP_2,
        OFFSET_VERTICAL_UP_3,
        OFFSET_VERTICAL_UP_4,
        OFFSET_VERTICAL_UP_5,
        OFFSET_VERTICAL_UP_6,
        OFFSET_VERTICAL_UP_7,

        OFFSET_VERTICAL_DOWN_1,
        OFFSET_VERTICAL_DOWN_2,
        OFFSET_VERTICAL_DOWN_3,
        OFFSET_VERTICAL_DOWN_4,
        OFFSET_VERTICAL_DOWN_5,
        OFFSET_VERTICAL_DOWN_6,
        OFFSET_VERTICAL_DOWN_7,

        //Bishop
        OFFSET_DIAGONAL_UP_RIGHT_1,
        OFFSET_DIAGONAL_UP_RIGHT_2,
        OFFSET_DIAGONAL_UP_RIGHT_3,
        OFFSET_DIAGONAL_UP_RIGHT_4,
        OFFSET_DIAGONAL_UP_RIGHT_5,
        OFFSET_DIAGONAL_UP_RIGHT_6,
        OFFSET_DIAGONAL_UP_RIGHT_7,

        OFFSET_DIAGONAL_UP_LEFT_1,
        OFFSET_DIAGONAL_UP_LEFT_2,
        OFFSET_DIAGONAL_UP_LEFT_3,
        OFFSET_DIAGONAL_UP_LEFT_4,
        OFFSET_DIAGONAL_UP_LEFT_5,
        OFFSET_DIAGONAL_UP_LEFT_6,
        OFFSET_DIAGONAL_UP_LEFT_7,

        OFFSET_DIAGONAL_DOWN_LEFT_1,
        OFFSET_DIAGONAL_DOWN_LEFT_2,
        OFFSET_DIAGONAL_DOWN_LEFT_3,
        OFFSET_DIAGONAL_DOWN_LEFT_4,
        OFFSET_DIAGONAL_DOWN_LEFT_5,
        OFFSET_DIAGONAL_DOWN_LEFT_6,
        OFFSET_DIAGONAL_DOWN_LEFT_7,

        OFFSET_DIAGONAL_DOWN_RIGHT_1,
        OFFSET_DIAGONAL_DOWN_RIGHT_2,
        OFFSET_DIAGONAL_DOWN_RIGHT_3,
        OFFSET_DIAGONAL_DOWN_RIGHT_4,
        OFFSET_DIAGONAL_DOWN_RIGHT_5,
        OFFSET_DIAGONAL_DOWN_RIGHT_6,
        OFFSET_DIAGONAL_DOWN_RIGHT_7
    };

    public Queen(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        Position potentialMove = new Position();
        List<Move> legalMoves = new ArrayList<Move>();

        for (Position each : ALLOWED_MOVES) {
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
