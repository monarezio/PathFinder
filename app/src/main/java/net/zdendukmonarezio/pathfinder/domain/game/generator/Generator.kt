package net.zdendukmonarezio.pathfinder.domain.game.generator

import net.zdendukmonarezio.pathfinder.domain.game.maze.Maze
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate

/**
 * Created by monarezio on 04/06/2017.
 */
interface Generator {

    /**
     * Generates a maze rows by columns with the starting position at start
     */
    fun createMaze(start: Coordinate): Maze

    fun getRows(): Int

    fun getColumns(): Int

}