package net.zdendukmonarezio.pathfinder.presentation.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.zdendukmonarezio.pathfinder.R;
import net.zdendukmonarezio.pathfinder.domain.mazes.Mazes;
import net.zdendukmonarezio.pathfinder.presentation.game.GameActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.level_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Intent intent = getIntent();
        mAdapter = new MyAdapter(Mazes.getInstance().getMazes(this).toBlocking().first().component1(), this);

        // set listener for RecyclerView
        mAdapter.setOnClickListener(position -> {
            startGame(position);
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    private void startGame(int position) {
        Intent intent1 = new Intent(this, GameActivity.class);
        intent1.putExtra("id", position);
        startActivity(intent1);
    }
}
