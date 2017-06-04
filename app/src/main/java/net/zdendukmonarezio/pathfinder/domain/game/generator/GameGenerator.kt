package net.zdendukmonarezio.pathfinder.domain.game.generator

import net.zdendukmonarezio.pathfinder.domain.common.extensions.random
import net.zdendukmonarezio.pathfinder.domain.game.maze.Game
import net.zdendukmonarezio.pathfinder.domain.game.maze.Maze
import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board
import net.zdendukmonarezio.pathfinder.domain.game.model.board.GameBoard
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Path

/**
 * Created by monarezio on 04/06/2017.
 */
class GameGenerator constructor(private val rows: Int, private val columns: Int) : Generator {

    override fun createMaze(start: Coordinate): Maze = createMaze(start, Game.createEmpty(rows, columns))

    fun createMaze(start: Coordinate, maze: Maze, path: Path = Path.createEmpty(), blackList: Path = Path.createEmpty()): Maze {
        println(start)
        val moves = maze.getAvailableMoves(start).toList()
                .filter { i -> !path.coordinates.contains(start.getNextCoordinate(i)) }
                .filter { i -> !blackList.coordinates.contains(start.getNextCoordinate(i)) }
        if(moves.isEmpty()) {
            val junction = maze.backTrack(start, path, blackList)
            if(!junction.isEmpty()) {
                val dif = path - junction
                createMaze(dif.last(), maze, path + dif, blackList + junction)
            }

            return maze
        } else {
            val nextDirection = moves[Int.random(0, moves.size - 1)]
            return createMaze(
                    start.getNextCoordinate(nextDirection),
                    addWalls(start, nextDirection, maze, blackList + path),
                    path + start,
                    blackList
            )
        }
    }

    private fun addWalls(pos: Coordinate, direction: Direction, maze: Maze, blackList: Path): Maze {
        fun addWall(coords: List<Coordinate>, board: Board): Board {
            if(coords.isEmpty())
                return board
            return addWall(coords.drop(1), board.set(coords.first().x, coords.first().y, Field.SOLID))
        }

        val nextMoves = maze.getAvailableMoves(pos.getNextCoordinate(direction))
                .filter { i -> !blackList.coordinates.contains(pos.getNextCoordinate(i)) }
        val walls = if(Direction.getHorizontal().contains(direction)) nextMoves.filter { i -> !Direction.getHorizontal().contains(i) }
        else nextMoves.filter { i -> !Direction.getVertical().contains(i) }
        return Game.createMaze(
                addWall(walls.map { i -> pos.getNextCoordinate(i) }, maze.getBoard())
        )
    }

    override fun getRows(): Int = rows

    override fun getColumns() = columns

}