package net.zdendukmonarezio.pathfinder.data.maze

import android.content.Context
import net.zdendukmonarezio.pathfinder.domain.common.extensions.readTextAndClose
import net.zdendukmonarezio.pathfinder.domain.maze.MazeLoader
import rx.Observable

/**
 * Created by monarezio on 22/04/2017.
 */
class MazeData : MazeLoader {

    override fun getRawMazeData(context: Context, name: String): Observable<String> {
        return Observable.just(context.assets.open("mazes/" + name + ".json").readTextAndClose())
    }
}