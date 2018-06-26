package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board,whiteStandardLegalMoves,blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return m_board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return m_board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {

        final List<Move> kingCastles = new ArrayList<>();
        if (m_playerKing.isFirstMove() && !this.isInCheck()) {
            //white king side castle
            if (!m_board.getTile(61).isTileOccupied() &&
                    !m_board.getTile(62).isTileOccupied()) {

                final Tile rookTile = m_board.getTile(63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new KingSideCastleMove(m_board,
                                                               m_playerKing,
                                                               62,
                                                               (Rook)rookTile.getPiece(),
                                                               rookTile.getTileCoordinate(),
                                                               61));
                    }
                }
            }
            if (!m_board.getTile(57).isTileOccupied() &&
                    !m_board.getTile(58).isTileOccupied() &&
                    !m_board.getTile(59).isTileOccupied()) {

                final Tile rookTile = m_board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnTile(58, opponentsLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(59, opponentsLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new QueenSideCastleMove(m_board,
                                                            m_playerKing,
                                                            58,
                                                            (Rook)rookTile.getPiece(),
                                                            rookTile.getTileCoordinate(),
                                                            59));
                }
            }
        }

        return null;
    }
}
