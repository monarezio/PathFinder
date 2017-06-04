package net.zdendukmonarezio.pathfinder.domain.game.maze

import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Path

/**
 * Created by monarezio on 22/04/2017.
 */
interface Maze {

    /**
     * returns the players position, also you can build the entire maze from the players position
     */
    fun getPlayerPosition(): Coordinate

    /**
     * returns the enemy position, if no enemy returns (-1, -1)
     */
    fun getEnemyPosition(): Coordinate

    /**
     * returns a set of next moves, form the position of the player.
     */
    fun getAvailableMoves(): Set<Direction>

    /**
     * returns a set of next moves, form the position given as the argument.
     */
    fun getAvailableMoves(pos: Coordinate): Set<Direction>

    /**
     * returns a new Loader with direction moved to the new direction
     */
    fun move(direction: Direction): Maze

    /**
     * returns the board
     */
    fun getBoard(): Board


    /**
     * returns the correct path
     */
    fun getPath(from: Coordinate, to: Coordinate): Path

    /**
     * returns true if there is no finish field
     */
    fun didWin(): Boolean

    /**
     * returns true if there is no player field
     */
    fun didLoose(): Boolean

    /**
     * swaps two fields
     */
    fun swapFields(pos: Coordinate, direction: Direction): Maze

    /**
     * swaps two fields
     */
    fun swapFields(pos: Coordinate, newPos: Coordinate): Maze

    /**
     * Return the path to the last junction
     */
    fun backTrack(from: Coordinate, tmpPath: Path, blackList: Path = Path.createEmpty()): Path
}