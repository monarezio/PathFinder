package net.zdendukmonarezio.pathfinder.presentation.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import net.zdendukmonarezio.pathfinder.R;
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board;
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(GamePresenter.class)
public class GameActivity extends NucleusActivity<GamePresenter> implements GameView {

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        int gameId = getIntent().getIntExtra("id", 0);

        GamePresenter presenter = getPresenter();
        presenter.setupGame(gameId, this);
    }

    @Override
    public void showGameBoard(Board gameBoard) {
        System.out.println("vymrdanec");
        System.out.println(gameBoardLayout);
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void warn(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.arrow_up)
    public void moveUp(View view) {
        getPresenter().onMoveMade(Direction.NORTH);
    }

    @OnClick(R.id.arrow_down)
    public void moveDown(View view) {
        getPresenter().onMoveMade(Direction.SOUTH);
    }

    @OnClick(R.id.arrow_left)
    public void moveLeft(View view) {
        getPresenter().onMoveMade(Direction.WEST);
    }

    @OnClick(R.id.arrow_right)
    public void moveRight(View view) {
        getPresenter().onMoveMade(Direction.EAST);
    }


}
