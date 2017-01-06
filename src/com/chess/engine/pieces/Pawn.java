package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.PieceColor;
import com.chess.engine.board.*;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece {
    private final static Position OFFSET_VERTICAL_1 = new Position(0, 1);
    private final static Position OFFSET_VERTICAL_2 = new Position(0, 2);
    private final static Position OFF_SET_DIAGONAL_RIGHT_1 = new Position(0, 2);
    private final static Position OFF_SET_DIAGONAL_LEFT_1 = new Position(0, 2);

    private List<Move> legalMoves = new ArrayList<Move>();

    private final int UP = 1;
    private final int DOWN = -1;
    private final int UP_OFFSET_FIRST_MOVE = 2;
    private final int DOWN_OFFSET_FIRST_MOVE = -2;
    private boolean firstMove = true;

    public final static Position[] ALLOWED_MOVES = {
            OFFSET_VERTICAL_1,
            OFFSET_VERTICAL_2,
            OFF_SET_DIAGONAL_RIGHT_1,
            OFF_SET_DIAGONAL_LEFT_1
    };

    public Pawn(Position piecePosition, PieceColor pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        //Jump 2 moves as first move
        if (isFirstMove()) {
            jumpTwoSquares(board);
        } else {
            jumpOneSquare(board);
        }

        //Add attacking move
        if (this.isWhite()) {
            attackingWhiteMove(board);
        } else {
            attackingBlackMove(board);
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private void jumpOneSquare(Board board) {
        Position potentialMovePosition = new Position();
        //Jump 1 square non-first move
        Position inFrontOfCurrentPosition;

        //Initiate potentialMovePosition
        if (this.isWhite()) {
            potentialMovePosition.setX( this.piecePosition.getX() + OFFSET_VERTICAL_1.getX() );
            potentialMovePosition.setY( this.piecePosition.getY() + OFFSET_VERTICAL_1.getY() );

            inFrontOfCurrentPosition = potentialMovePosition;
        } else {
            potentialMovePosition.setX( this.piecePosition.getX() - OFFSET_VERTICAL_1.getX() );
            potentialMovePosition.setY( this.piecePosition.getY() - OFFSET_VERTICAL_1.getY() );

            inFrontOfCurrentPosition = potentialMovePosition;
        }

        //Add Legal First Potential Major Moves
        if (BoardUtils.isValidCoordinate(potentialMovePosition)) {
            final Tile potentialTile = board.getTile(potentialMovePosition);
            if ( !potentialTile.isOccupied()
                    && !board.getTile(inFrontOfCurrentPosition).isOccupied() ) {
                //Move is still not implemented here
                legalMoves.add(new MajorMove(board, this, potentialMovePosition));
            }
        }
    }

    private void jumpTwoSquares(Board board) {
        Position potentialMovePosition = new Position();
        for (Position each : ALLOWED_MOVES) {
            Position inFrontOfCurrentPosition = new Position();

            //Initiate potentialMovePosition and move in front of potentialMovePosition
            if (this.isWhite()) {
                potentialMovePosition.setX( this.piecePosition.getX() + each.getX() );
                potentialMovePosition.setY( this.piecePosition.getY() + (each.getY() * UP) );

                inFrontOfCurrentPosition.setX(this.piecePosition.getX());
                inFrontOfCurrentPosition.setY(this.piecePosition.getY() + 1);
            } else {
                potentialMovePosition.setX( this.piecePosition.getX() + each.getX() );
                potentialMovePosition.setY( this.piecePosition.getY() + (each.getY() * DOWN) );

                inFrontOfCurrentPosition.setX(this.piecePosition.getX());
                inFrontOfCurrentPosition.setY(this.piecePosition.getY() - 1);
            }

            //First Move is always inside the board
            //Add Legal First Potential Moves
            final Tile potentialTile = board.getTile(potentialMovePosition);
            if ( !potentialTile.isOccupied() ) {
                //Move is still not implemented here
                legalMoves.add(new MajorMove(board, this, potentialMovePosition));
            }
        }
    }

    private void attackingWhiteMove(Board board) {
        //Get Attacking position
        Position diagonalLeftOfCurrentPosition = new Position();
        Position diagonalRightOfCurrentPosition = new Position();

        //Set Attacking position for white pawn
        diagonalLeftOfCurrentPosition.setX(this.piecePosition.getX() - 1);
        diagonalLeftOfCurrentPosition.setY(this.piecePosition.getY() + 1);

        diagonalRightOfCurrentPosition.setX(this.piecePosition.getX() + 1);
        diagonalRightOfCurrentPosition.setY(this.piecePosition.getY() + 1);

        //Attack moves for each diagonal left/right
        if ( BoardUtils.isValidCoordinate(diagonalLeftOfCurrentPosition)
                && board.getTile(diagonalLeftOfCurrentPosition)
                .getPiece()
                .getPieceColor() != this.getPieceColor() ) {
            //Add legal attacking move
            legalMoves.add(new AttackMove(board, this,
                    diagonalLeftOfCurrentPosition,
                    board.getTile(diagonalLeftOfCurrentPosition).getPiece()));
        }

        if ( BoardUtils.isValidCoordinate(diagonalRightOfCurrentPosition)
                && board.getTile(diagonalRightOfCurrentPosition)
                .getPiece()
                .getPieceColor() != this.getPieceColor() ) {
            //Add legal attacking move
            legalMoves.add(new AttackMove(board, this,
                    diagonalRightOfCurrentPosition,
                    board.getTile(diagonalRightOfCurrentPosition).getPiece()));
        }
    }

    private void attackingBlackMove(Board board) {
        //Get Attacking position
        Position diagonalLeftOfCurrentPosition = new Position();
        Position diagonalRightOfCurrentPosition = new Position();

        //Set Attacking position for white pawn
        diagonalLeftOfCurrentPosition.setX(this.piecePosition.getX() - 1);
        diagonalLeftOfCurrentPosition.setY(this.piecePosition.getY() - 1);

        diagonalRightOfCurrentPosition.setX(this.piecePosition.getX() + 1);
        diagonalRightOfCurrentPosition.setY(this.piecePosition.getY() - 1);

        //Attack moves for each diagonal left/right
        if ( BoardUtils.isValidCoordinate(diagonalLeftOfCurrentPosition)
                && board.getTile(diagonalLeftOfCurrentPosition)
                .getPiece()
                .getPieceColor() != this.getPieceColor() ) {
            //Add legal attacking move
            legalMoves.add(new AttackMove(board, this,
                    diagonalLeftOfCurrentPosition,
                    board.getTile(diagonalLeftOfCurrentPosition).getPiece()));
        }

        if ( BoardUtils.isValidCoordinate(diagonalRightOfCurrentPosition)
                && board.getTile(diagonalRightOfCurrentPosition)
                .getPiece()
                .getPieceColor() != this.getPieceColor() ) {
            //Add legal attacking move
            legalMoves.add(new AttackMove(board, this,
                    diagonalRightOfCurrentPosition,
                    board.getTile(diagonalRightOfCurrentPosition).getPiece()));
        }
    }

    private boolean isFirstMove() {
        return this.firstMove;
    }

    private void madeFirstMove() {
        this.firstMove = false;
    }

}
