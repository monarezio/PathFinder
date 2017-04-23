package net.zdendukmonarezio.pathfinder.domain.mazes

import android.content.Context
import net.zdendukmonarezio.pathfinder.data.mazes.MazesData

/**
 * Created by monarezio on 23/04/2017.
 */
class Mazes private constructor(){
    val loader: MazesLoader = MazesData()

    fun getMazes(context: Context) = loader.getListOfMazes(context)

    companion object {
        @JvmStatic val intance = Mazes()
    }

}