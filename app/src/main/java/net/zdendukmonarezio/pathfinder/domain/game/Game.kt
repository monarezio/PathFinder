package net.zdendukmonarezio.pathfinder.domain.game

import android.content.Context
import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.data.maze.MazeData
import net.zdendukmonarezio.pathfinder.domain.common.extensions.isInBounds
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board
import net.zdendukmonarezio.pathfinder.domain.game.model.board.GameBoard
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Path

/**
 * Created by monarezio on 22/04/2017.
 */
class Game private constructor(private val board: Board) : Maze{

    override fun getPlayerPosition(): Coordinate = board.find(Field.PLAYER)

    override fun getAvailableMoves(pos: Coordinate): Set<Direction> {
        return mapOf<Direction, Coordinate>(Pair(Direction.NORTH, pos.north()), Pair(Direction.EAST, pos.east()),
                Pair(Direction.SOUTH, pos.south()), Pair(Direction.WEST, pos.west()))
                .filter { i -> board.getFields().isInBounds(i.value)}
                .filter { i -> board.getFields()[i.value.y][i.value.x] != Field.SOLID  }
                .map { i -> i.key }.toSet()
    }

    override fun getAvailableMoves(): Set<Direction> = getAvailableMoves(getPlayerPosition())

    override fun move(direction: Direction): Maze {
        if(!getAvailableMoves().contains(direction))
            return this

        val pos = getPlayerPosition()

        val board = this.board.set(pos, Field.AIR) //Remove the player from the current position

        if(direction == Direction.NORTH)
            return createMaze(board.set(pos.north(), Field.PLAYER))
        else if(direction == Direction.EAST)
            return createMaze(board.set(pos.east(), Field.PLAYER))
        else if(direction == Direction.SOUTH)
            return createMaze(board.set(pos.south(), Field.PLAYER))
        return createMaze(board.set(pos.west(), Field.PLAYER))
    }

    override fun getBoard(): Board = board

    private fun getPath(from: Coordinate, to: Coordinate, tmpPath: Path): Path { //TODO: Repair a bug where no path found when x is the biggest
        if(to == from)
            return tmpPath

        val nextMoves = from.getNextCoordinates(getAvailableMoves(from))
                .filter { i -> !tmpPath.coordinates.contains(i) }

        println(from.toString() + " => " + nextMoves)

        if(nextMoves.isEmpty()) {
            println("---------------")
            return Path.createEmpty()
        }

        return nextMoves
                .map { i -> getPath(i, to, tmpPath + i) }
                .sorted().first()
    }

    override fun getPath(from: Coordinate, to: Coordinate): Path = getPath(from, to, Path.create(listOf(from)))

    override fun didLoose(): Boolean = getPlayerPosition().isEmpty()

    override fun didWin(): Boolean = board.find(Field.FINISH).isEmpty()


    companion object {

        @JvmStatic fun createMaze(board: Board): Maze = Game(board)

        @JvmStatic fun createFromFile(context: Context, fileName: String): Maze {
            val gson = GsonBuilder().create()
            val json = MazeData().getRawMazeData(context, fileName).toBlocking().first()
            return createMaze(gson.fromJson(json, GameBoard::class.java))
        }

    }
}