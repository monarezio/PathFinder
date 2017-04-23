package net.zdendukmonarezio.pathfinder.data.maze

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.domain.common.extensions.readTextAndClose
import net.zdendukmonarezio.pathfinder.domain.game.Maze
import net.zdendukmonarezio.pathfinder.domain.maze.Loader
import net.zdendukmonarezio.pathfinder.domain.maze.exceptions.InvalidJsonObject
import rx.Observable

/**
 * Created by monarezio on 22/04/2017.
 */
class MazeData : Loader {

    override fun getRawMazeData(context: Context, name: String): Observable<String> {
        return Observable.just(context.assets.open("mazes/" + name + ".json").readTextAndClose())
    }

    override fun getJsonMazeData(context: Context, name: String): Observable<Map<*, *>> {
        val gson = GsonBuilder().create()
        val json = getRawMazeData(context, name).toBlocking().first()
        val map = gson.fromJson(json, Object::class.java)

        if(map is Map<*, *>)
            return Observable.just(map)
        else
            throw InvalidJsonObject("Json object could not be parsed into a map")
    }

}