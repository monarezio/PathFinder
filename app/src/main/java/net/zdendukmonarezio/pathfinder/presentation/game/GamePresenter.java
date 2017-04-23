package net.zdendukmonarezio.pathfinder.presentation.game;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import net.zdendukmonarezio.pathfinder.domain.game.Game;
import net.zdendukmonarezio.pathfinder.domain.game.Maze;
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board;
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction;
import net.zdendukmonarezio.pathfinder.domain.mazes.Mazes;
import net.zdendukmonarezio.pathfinder.presentation.Presenter;

import java.util.List;

import kotlin.Pair;

import static android.R.attr.x;
import static android.R.attr.y;

public class GamePresenter extends Presenter<GameView> {

    private Maze game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    public void onMoveMade(Direction direction) {
        presentMove(direction);
    }

    private void presentMove(Direction direction) {
        game = game.move(direction);
        Board newGameBoard = game.getBoard();
        viewIfExists().subscribe(view -> {
            view.showGameBoard(newGameBoard);
        });
    }

    public void setupGame(int id, Context context) {
        game = Game.createFromFile(context, Mazes.getIntance().getMazes(context).toBlocking().first().component1().get(id).getFileName());
        viewIfExists().subscribe(view -> {
            view.showGameBoard(game.getBoard());
        });
    }
}
