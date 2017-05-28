package net.zdendukmonarezio.pathfinder.domain.common.extensions

import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.domain.game.Game
import net.zdendukmonarezio.pathfinder.domain.game.model.board.GameBoard
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field

/**
 * Created by monarezio on 22/04/2017.
 */

fun main(args: Array<String>) {
    var game = Game.createMaze(GameBoard.createBoard(listOf(
            listOf(Field.AIR, Field.FINISH, Field.AIR, Field.AIR),
            listOf(Field.AIR, Field.AIR, Field.AIR, Field.AIR),
            listOf(Field.AIR, Field.AIR, Field.PLAYER, Field.AIR),
            listOf(Field.AIR, Field.AIR, Field.AIR, Field.AIR)
    )))

    game = game.move(Direction.SOUTH)
    game = game.move(Direction.WEST)
    game = game.move(Direction.WEST)
    game = game.move(Direction.NORTH)
    println(game.getBoard())
}