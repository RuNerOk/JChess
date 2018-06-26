package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class MoveTransition {
    private final Board m_transitionBoard;
    private final Move m_move;
    private final MoveStatus m_moveStatus;

    public MoveTransition(final Board transitionBoard,
                          final Move move,
                          final MoveStatus moveStatus){
        m_transitionBoard = transitionBoard;
        m_move = move;
        m_moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return m_moveStatus;
    }

    public Board getTransitionBoard() {
        return m_transitionBoard;
    }
}
