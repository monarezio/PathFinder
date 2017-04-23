package net.zdendukmonarezio.pathfinder.presentation.game;

import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board;

public interface GameView {
    void showGameBoard(Board gameBoard);

    void warn(String message);
}
