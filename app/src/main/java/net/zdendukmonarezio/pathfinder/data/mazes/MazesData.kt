package net.zdendukmonarezio.pathfinder.data.mazes

import android.content.Context
import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.domain.common.extensions.readTextAndClose
import net.zdendukmonarezio.pathfinder.domain.mazes.MazesLoader
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeList
import rx.Observable

/**
 * Created by monarezio on 23/04/2017.
 */
class MazesData: MazesLoader {

    override fun getListOfMazes(context: Context): Observable<MazeList> {
        val gson = GsonBuilder().create()
        return Observable.just(gson.fromJson(context.assets.open("mazes.json").readTextAndClose(), MazeList::class.java))
    }

}