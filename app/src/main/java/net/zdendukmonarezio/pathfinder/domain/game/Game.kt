package net.zdendukmonarezio.pathfinder.domain.game

import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field

/**
 * Created by monarezio on 22/04/2017.
 */
class Game private constructor(private val board: Board) : Maze{

    override fun getPlayerPosition(): Coordinate = board.find(Field.PLAYER)

    override fun getAvailableMoves(): Set<Direction> {
        TODO("Not implemented yet")
    }

    override fun move(direction: Direction): Maze {
        val playerPos = getPlayerPosition()

        if(direction == Direction.NORTH)
            return createMaze(board.set(playerPos.x, playerPos.y - 1, Field.PLAYER))
        else if(direction == Direction.EAST)
            return createMaze(board.set(playerPos.x + 1, playerPos.y, Field.PLAYER))
        else if(direction == Direction.SOUTH)
            return createMaze(board.set(playerPos.x, playerPos.y + 1, Field.PLAYER))
        return createMaze(board.set(playerPos.x - 1, playerPos.y, Field.PLAYER))
    }

    override fun getBoard(): Board = board


    companion object {

        @JvmStatic fun createMaze(board: Board): Maze = Game(board)

    }
}