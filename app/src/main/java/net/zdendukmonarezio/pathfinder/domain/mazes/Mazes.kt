package net.zdendukmonarezio.pathfinder.domain.mazes

import android.content.Context
import net.zdendukmonarezio.pathfinder.data.mazes.MazesData
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeList
import rx.Observable

/**
 * Created by monarezio on 23/04/2017.
 */
class Mazes private constructor(){
    val loader: MazesLoader = MazesData()

    fun getMazes(context: Context): Observable<MazeList> = loader.getListOfMazes(context)

    fun setFinished(context: Context, fileName: String) = loader.setFinished(context, fileName)

    companion object {
        @JvmStatic val intance = Mazes()
    }

}