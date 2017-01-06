package com.chess.engine.board;

import com.chess.engine.PieceColor;
import com.chess.engine.pieces.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.*;

public class Board {

    private Map<Position, Tile> gameBoard;
    private Collection<Piece> activeWhitePieces;
    private Collection<Piece> activeBlackPieces;

    public Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.activeBlackPieces = getActivePieces(this.gameBoard, PieceColor.BLACK);
        this.activeWhitePieces = getActivePieces(this.gameBoard, PieceColor.WHITE);
    }

    private Collection<Piece> getActivePieces(Map<Position, Tile> gameBoard, PieceColor color) {
        Collection<Piece> activePieces = new ArrayList<>();

        //loop through board
        for (Map.Entry<Position, Tile> each : gameBoard.entrySet()) {
            //Check tile occupied
            if (each.getValue().isOccupied()
                    && each.getValue().getPiece().getPieceColor() == color) {
                //add active piece
                activePieces.add(each.getValue().getPiece());
            }
        }

        return ImmutableList.copyOf(activePieces);
    }

    public Map<Position, Tile> createGameBoard(final Builder builder) {
        Map<Position, Tile> tiles = new HashMap<>();
        Position tilePosition = new Position();

        for (int i = 0; i < BoardUtils.NUM_ROWS; i++) {
            tilePosition.setX(i);
            for (int j = 0; j < BoardUtils.NUM_COLS; j++) {
                tilePosition.setY(j);
                tiles.put(tilePosition, Tile.createTile(tilePosition, builder.boardConfig.get(tilePosition)));
            }
        }

        return ImmutableMap.copyOf(tiles);
    }

    public Tile getTile(Position position) {
        return gameBoard.get(position);
    }

    public Board initializeBoard() {
        final Builder builder = new Builder();
        Position piecePosition = new Position();

        //Set White Pawn
        piecePosition.setX(1);
        for (int i = 0; i < 8; i++) {
            piecePosition.setY(i);
            builder.setPiece(piecePosition, new Pawn(piecePosition, PieceColor.WHITE));
        }

        //Set Black Pawn
        piecePosition.setX(6);
        for (int i = 0; i < 8; i++) {
            piecePosition.setY(i);
            builder.setPiece(piecePosition, new Pawn(piecePosition, PieceColor.BLACK));
        }

        //Set White Pieces
        piecePosition.setXY(0, 0);
        builder.setPiece(piecePosition, new Rook(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 1);
        builder.setPiece(piecePosition, new Knight(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 2);
        builder.setPiece(piecePosition, new Bishop(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 3);
        builder.setPiece(piecePosition, new Queen(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 4);
        builder.setPiece(piecePosition, new King(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 5);
        builder.setPiece(piecePosition, new Bishop(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 6);
        builder.setPiece(piecePosition, new Knight(piecePosition, PieceColor.WHITE));
        piecePosition.setXY(0, 7);
        builder.setPiece(piecePosition, new Rook(piecePosition, PieceColor.WHITE));

        //Set Black Pieces
        piecePosition.setXY(0, 0);
        builder.setPiece(piecePosition, new Rook(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 1);
        builder.setPiece(piecePosition, new Knight(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 2);
        builder.setPiece(piecePosition, new Bishop(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 3);
        builder.setPiece(piecePosition, new Queen(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 4);
        builder.setPiece(piecePosition, new King(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 5);
        builder.setPiece(piecePosition, new Bishop(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 6);
        builder.setPiece(piecePosition, new Knight(piecePosition, PieceColor.BLACK));
        piecePosition.setXY(0, 7);
        builder.setPiece(piecePosition, new Rook(piecePosition, PieceColor.BLACK));

        //First Move White
        builder.setNextMovePiece(PieceColor.WHITE);

        return builder.build();
    }

    public static class Builder {
        Map<Position, Piece> boardConfig;
        private PieceColor nextMovePiece;

        public Builder() {
            boardConfig = new HashMap<>();
        }

        public Builder setPiece(Position position, Piece piece) {
            this.boardConfig.put(position, piece);
            return this;
        }

        public Builder setNextMovePiece(PieceColor color) {
            this.nextMovePiece = color;
            return this;
        }

        public PieceColor getNextMovePiece() {
            return nextMovePiece;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
