package com.chess.engine.pieces;

import com.chess.engine.PieceColor;
import com.chess.engine.board.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trai on 1/4/17.
 */
public class King extends Piece {
    private final Position OFF_SET_VERTICAL_UP = new Position(0, 1);
    private final Position OFF_SET_VERTICAL_DOWN = new Position(0, 1);

    private final Position OFF_SET_HORIZONTAL_RIGHT = new Position(1, 0);
    private final Position OFF_SET_HORIZONTAL_LEFT = new Position(-1, 0);

    private final Position OFF_SET_DIAGONAL_TOP_LEFT = new Position(-1, 1);
    private final Position OFF_SET_DIAGONAL_TOP_RIGHT = new Position(1, 1);

    private final Position OFF_SET_DIAGONAL_BOTTOM_LEFT = new Position(-1, -1);
    private final Position OFF_SET_DIAGONAL_BOTTOM_RIGHT = new Position(1,- 1);

    private List<Move> legalMoves = new ArrayList<>();

    private final Position[] ALLOWED_MOVES = new Position[]{
            OFF_SET_VERTICAL_UP,
            OFF_SET_VERTICAL_DOWN,
            OFF_SET_HORIZONTAL_RIGHT,
            OFF_SET_HORIZONTAL_LEFT,
            OFF_SET_DIAGONAL_TOP_LEFT,
            OFF_SET_DIAGONAL_TOP_RIGHT,
            OFF_SET_DIAGONAL_BOTTOM_LEFT,
            OFF_SET_DIAGONAL_BOTTOM_RIGHT
    };

    public King(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        Position potentialMovePosition = new Position();

        for (Position each : ALLOWED_MOVES) {
            /* check X coordinate for each move is make */
            potentialMovePosition.setX( this.piecePosition.getX() + each.getX() );
            potentialMovePosition.setY( this.piecePosition.getY() + each.getY() );

            addLegalMovesConditionally(board, potentialMovePosition);

        }

        return ImmutableList.copyOf(legalMoves);
    }

    private void addLegalMovesConditionally(Board board, Position potentialMovePosition) {
        if (BoardUtils.isValidCoordinate(potentialMovePosition)) {
            final Tile targetedTile = board.getTile(potentialMovePosition);
            if (!targetedTile.isOccupied()) {
                //Move is still not implemented here
                legalMoves.add(new MajorMove(board, this, potentialMovePosition));
            } else {
                //if the tile selected is Occupied
                final Piece targetedPiece = targetedTile.getPiece();
                //Check if the piece is enemy or friend
                final PieceColor targetedPieceColor = targetedPiece.getPieceColor();
                if (this.pieceColor != targetedPieceColor) {
                    //Different type of move here for attacking
                    //Attack Move
                    legalMoves.add(new AttackMove(board, this, potentialMovePosition, targetedPiece));
                }
            }
        }
    }
}
