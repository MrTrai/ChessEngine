package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public class Tile {
    private static final Map<Position, Tile> EMPTY_MAP_CACHE = createEmptyTileMap();
    private final Position tileCoordinate;
    private boolean occupied = false;
    private Piece currentPiece = null;

    private Tile(Position coordinate, boolean occupied) {
        this.tileCoordinate = coordinate;
        this.occupied = occupied;
    }

    //Create new Occupied tile or get cached Empty tile
    public static Tile createTile(Position tileCoordinate, Piece piece) {
        return piece != null ?
            new Tile(tileCoordinate, true) :
            EMPTY_MAP_CACHE.get(tileCoordinate);
    }

    //initialize EMPTY_MAP
    //Cache Empty tiles map
    private static Map<Position, Tile> createEmptyTileMap() {
        final Map<Position, Tile> emptyMap = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            Position position = new Position();
            position.setX(i);
            for (int j = 0; j < 8; j++) {
                position.setY(j);
                emptyMap.put(position, new Tile(position, false));
            }
        }
        //use from Guava dependency
        return ImmutableMap.copyOf(emptyMap);
    }

    public Position getTileCoordinate() {
        return tileCoordinate;
    }

    public boolean isOccupied() {
        return this.occupied;
    }
    public Piece getPiece() {
        return this.currentPiece;
    }

    //Emptied Tile
//    public static final class Tile extends com.chess.engine.board.Tile {
//        private Tile(Position coordinate) {
//            this.Tile(coordinate);
//        }
//
//        @Override
//        public boolean isOccupied() {
//            return false;
//        }
//
//        @Override
//        public Piece getPiece() {
//            return null;
//        }
//    }

    //Occupied Tile 
//    public static final class OccupiedTile extends com.chess.engine.board.Tile {
//
//        private final Piece currentPiece;
//
//        private OccupiedTile(Position coordinate, final Piece piece) {
//            super(coordinate);
//            this.currentPiece = piece;
//        }
//
//        @Override
//        public boolean isOccupied() {
//            return true;
//        }
//
//        @Override
//        public Piece getPiece() {
//            return currentPiece;
//        }
//    }
}
