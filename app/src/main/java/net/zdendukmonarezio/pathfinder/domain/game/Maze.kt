package net.zdendukmonarezio.pathfinder.domain.game

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
}