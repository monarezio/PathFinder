package net.zdendukmonarezio.pathfinder.data.mazes

import android.content.Context
import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.R
import net.zdendukmonarezio.pathfinder.domain.common.extensions.readTextAndClose
import net.zdendukmonarezio.pathfinder.domain.mazes.MazesLoader
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeList
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeListBuilder
import rx.Observable

/**
 * Created by monarezio on 23/04/2017.
 */
class MazesData: MazesLoader {

    override fun getListOfMazes(context: Context): Observable<MazeList> {
        val gson = GsonBuilder().create()
        return Observable.just(
                gson.fromJson(
                        context.assets.open("mazes.json").readTextAndClose(), MazeListBuilder::class.java
                ).setFinished(
                        context.getSharedPreferences(context.getString(R.string.finished_levels), Context.MODE_PRIVATE)
                                .getStringSet(context.getString(R.string.levels), setOf())
                ).build()
        )
    }

    override fun setFinished(context: Context, fileName: String) {
        val ref = context.getSharedPreferences(context.getString(R.string.finished_levels), Context.MODE_PRIVATE)
        val set = ref.getStringSet(context.getString(R.string.levels), setOf())
        set.add(fileName)
        val editor = ref.edit()
        editor.putString(context.getString(R.string.levels), fileName)
        editor.commit()
    }

}