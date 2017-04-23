package net.zdendukmonarezio.pathfinder.domain.common.extensions

import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.domain.game.Maze
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board
import net.zdendukmonarezio.pathfinder.domain.game.model.board.GameBoard
import net.zdendukmonarezio.pathfinder.domain.mazes.models.MazeList
import rx.Observable

/**
 * Created by monarezio on 22/04/2017.
 */

fun main(args: Array<String>) {
    val json = "{ \"list\": [ { \"fileName\": \"0\", \"name\": \"First level\", \"description\": \"Some random description for the level.\" }, { \"fileName\": \"1\", \"name\": \"Second level\", \"description\": \"DOES NOT EXIST\" } ] }"
    val gson = GsonBuilder().create()

    println(gson.fromJson(json, MazeList::class.java))
}