package net.zdendukmonarezio.pathfinder.domain.maze

import android.content.Context
import com.google.gson.Gson
import rx.Observable

/**
 * Created by monarezio on 22/04/2017.
 */
interface Loader {

    /**
     * gets the raw file content of the maze
     */
    fun getRawMazeData(context: Context, name: String): Observable<String>

    /**
     * gets the file content and converts it into a maze
     */
    fun getJsonMazeData(context: Context, name: String): Observable<Map<*, *>>
}