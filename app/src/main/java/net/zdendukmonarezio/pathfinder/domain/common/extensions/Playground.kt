package net.zdendukmonarezio.pathfinder.domain.common.extensions

import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.domain.game.generator.GameGenerator
import net.zdendukmonarezio.pathfinder.domain.game.maze.Game
import net.zdendukmonarezio.pathfinder.domain.game.maze.Maze
import net.zdendukmonarezio.pathfinder.domain.game.model.board.GameBoard
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field

/**
 * Created by monarezio on 22/04/2017.
 */

fun main(args: Array<String>) {
    val a = GameGenerator(5, 5)
    val b = a.createMaze(Coordinate(0, 0)).getBoard() as GameBoard
    val g = GsonBuilder().create()
    print(g.toJson(b))
}