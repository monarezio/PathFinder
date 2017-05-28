package net.zdendukmonarezio.pathfinder.domain.mazes

import android.content.Context
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeList
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeListBuilder
import rx.Observable

/**
 * Created by monarezio on 23/04/2017.
 */
interface MazesLoader {

    /**
     * returns the MazeList class with the data mazes inside
     */
    fun getListOfMazes(context: Context): Observable<MazeList>

    fun setFinished(context: Context, fileName: String)

}