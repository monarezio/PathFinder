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

    override fun getEnemyPosition(): Coordinate = board.find(Field.ENEMY)

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

        val enemyPos = getEnemyPosition()

        val maze: Maze = if(!enemyPos.isEmpty()) {
            val path = getPath(enemyPos, pos)
            println("Path: " + path)
            val newEnemyPosition = if (!path.isEmpty()) path.coordinates[1] else enemyPos
            createMaze(board.set(enemyPos, Field.AIR).set(newEnemyPosition, Field.ENEMY))
        } else this

        if(!maze.didLoose())
            return createMaze(maze.getBoard().set(pos, Field.AIR).set(pos.getNextCoordinate(direction), Field.PLAYER)) //Move the player (have to replace the tiles)
        else
            return maze
    }

    override fun getBoard(): Board = board

    private fun getPath(from: Coordinate, to: Coordinate, tmpPath: Path, blackList: Path): Path {
        if(to == from)
            return tmpPath

        val nextMoves = from.getNextCoordinates(getAvailableMoves(from))
                .filter { i -> !tmpPath.coordinates.contains(i) }
                .filter { i -> !blackList.coordinates.contains(i) }

        if(nextMoves.isEmpty()) {
            val junction = backTrack(from, tmpPath, blackList)
            val dif = tmpPath - junction
            return getPath(dif.last(), to, dif, blackList + junction)
        }

        return nextMoves
                .map { i -> getPath(i, to, tmpPath + i, blackList) }
                .sorted().first()
    }

    fun backTrack(from: Coordinate, tmpPath: Path, blackList: Path = Path.createEmpty()): Path {
        if(tmpPath.getSize() <= 1 || from.getNextCoordinates(getAvailableMoves(from)).filter { i -> !blackList.coordinates.contains(i)}
                .size > 1)
            return blackList

        val nextPath = tmpPath.removeLast()
        return backTrack(nextPath.last(), nextPath, blackList + tmpPath.last())
    }

    override fun getPath(from: Coordinate, to: Coordinate): Path = getPath(from, to, Path.create(listOf(from)), Path.createEmpty())

    override fun didLoose(): Boolean = getPlayerPosition().isEmpty()

    override fun didWin(): Boolean = board.find(Field.FINISH).isEmpty()

    override fun swapFields(pos: Coordinate, newPos: Coordinate): Maze {
        val field = board.getField(pos)
        val targetField = board.getField(newPos)
        return createMaze(board.set(newPos, field).set(pos, targetField))
    }

    override fun swapFields(pos: Coordinate, direction: Direction): Maze = swapFields(pos, pos.getNextCoordinate(direction))

    companion object {

        @JvmStatic fun createMaze(board: Board): Maze = Game(board)

        @JvmStatic fun createFromFile(context: Context, fileName: String): Maze {
            val gson = GsonBuilder().create()
            val json = MazeData().getRawMazeData(context, fileName).toBlocking().first()
            return createMaze(gson.fromJson(json, GameBoard::class.java))
        }

    }
}