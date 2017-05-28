package net.zdendukmonarezio.pathfinder.presentation.game;

import android.content.Context;
import android.os.Bundle;

import net.zdendukmonarezio.pathfinder.domain.game.Game;
import net.zdendukmonarezio.pathfinder.domain.game.Maze;
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board;
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction;
import net.zdendukmonarezio.pathfinder.presentation.Presenter;

public class GamePresenter extends Presenter<GameView> {

    private Maze game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    public void onMoveMade(Direction direction) {
        /*game.didLoose() ? viewIfExists().subscribe(view -> {
            view.gameLost(false);
        }) : game.didWin() ? viewIfExists().subscribe(view -> {
            view.gameLost(true);
        }):presentMove(direction);
*/
        presentMove(direction);
        if (game.didLoose()) {
            viewIfExists().subscribe(view -> {
                view.gameLost();
            });
        } else if (game.didWin()) {
            viewIfExists().subscribe(view -> {
                view.gameWon();
            });
        }
    }

    private void presentMove(Direction direction) {
        game = game.move(direction);
        Board newGameBoard = game.getBoard();
        viewIfExists().subscribe(view -> {
            view.showGameBoard(newGameBoard);
        });
    }

    public void setupGame(int id, Context context) {
        game = Game.createFromFile(context, "" + id);
        viewIfExists().subscribe(view -> {
            view.showGameBoard(game.getBoard());
        });
    }
}
