package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final PieceType m_pieceType;
    protected final int m_piecePosition;
    protected final Alliance m_pieceAlliance;
    protected final boolean m_isFirstMove;
    private final int m_cachedHashCode;

    Piece(final PieceType pieceType,
          final int piecePosition,
          final Alliance pieceAlliance){
        m_pieceType = pieceType;
        //TODO exeption founds here
        m_piecePosition = piecePosition;
        m_pieceAlliance = pieceAlliance;
        //TODO more work here
        m_isFirstMove = false;
        m_cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = m_pieceType.hashCode();
        result = 31 * result + m_pieceAlliance.hashCode();
        result = 31 * result + m_piecePosition;
        result = 31 * result + (m_isFirstMove ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return m_piecePosition == otherPiece.getPiecePosition() &&
                m_pieceType == otherPiece.getPieceType() &&
                m_pieceAlliance == otherPiece.getPieceAlliance() &&
                m_isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return m_cachedHashCode;
    }

    public int getPiecePosition() {
        return m_piecePosition;
    }

    public Alliance getPieceAlliance() {
        return m_pieceAlliance;
    }

    public boolean isFirstMove() {
        return m_isFirstMove;
    }

    public PieceType getPieceType() {
        return m_pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public abstract Piece movePiece(Move move);

    public enum PieceType {
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };
        private String m_pieceName;
        PieceType(final String pieceName) {
            m_pieceName = pieceName;
        }

        @Override
        public String toString() {
            return m_pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();
    }
}
