package net.zdendukmonarezio.pathfinder.presentation.game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.zdendukmonarezio.pathfinder.R;
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board;
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction;
import net.zdendukmonarezio.pathfinder.domain.mazes.Mazes;
import net.zdendukmonarezio.pathfinder.domain.mazes.models.Maze;
import net.zdendukmonarezio.pathfinder.presentation.main.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(GamePresenter.class)
public class GameActivity extends NucleusActivity<GamePresenter> implements GameView {

    Mazes mazes;

    public static int gameId;

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @BindView(R.id.levelUp_announcer)
    TextView levelUp_announcer;

    @BindView(R.id.announcer_background)
    View announcer_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        gameId = getIntent().getIntExtra("id", 0);
        GamePresenter presenter = getPresenter();
        presenter.setupGame(gameId, this);
        announcer_background.setAlpha(0f);
        levelUp_announcer.setAlpha(0f);
        mazes = Mazes.getInstance();
    }

    @Override
    public void showGameBoard(Board gameBoard) {
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void gameLost() {
        levelUp_announcer.setText("You lose!");
        fadeIn(announcer_background, 500);
        fadeIn(levelUp_announcer, 500);
        levelUp_announcer.setAlpha(0f);
        announcer_background.setAlpha(0f);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }, 1000);
    }

    @Override
    public void gameWon() {
        mazes.setFinished(this, mazes.getMazes(this).toBlocking().first().component1().get(gameId).getFileName());
        gameId++;
        getPresenter().setupGame(gameId, this);
        levelUp_announcer.setText("You won!");
        fadeIn(announcer_background, 500);
        fadeIn(levelUp_announcer, 500);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            levelUp_announcer.setText(mazes.getMazes(this).toBlocking().first().component1().get(gameId).getName());
            handler.postDelayed(() -> {
                fadeOut(levelUp_announcer, 1000);
                fadeOut(announcer_background, 1000);
            }, 1000);
        }, 3000);
    }

    private void fadeIn(View view, int duration) {
        view.animate()
                .alpha(1f)
                .setDuration(duration)
                .setListener(null);
    }

    private void fadeOut(View view, int duration) {
        view.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
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
