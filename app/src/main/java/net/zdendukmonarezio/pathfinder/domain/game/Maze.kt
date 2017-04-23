package net.zdendukmonarezio.pathfinder.domain.game

import net.zdendukmonarezio.pathfinder.domain.game.model.board.Board
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Direction

/**
 * Created by monarezio on 22/04/2017.
 */
interface Maze {

    /**
     * returns the players position, also you can build the entire maze from the players position
     */
    fun getPlayerPosition(): Coordinate

    /**
     * returns the list of next moves. null means he can't go there
     */
    fun getAvailableMoves(): Set<Direction>

    /**
     * returns a new Loader with direction moved to the new direction
     */
    fun move(direction: Direction): Maze

    /**
     * returns the board
     */
    fun getBoard(): Board
}