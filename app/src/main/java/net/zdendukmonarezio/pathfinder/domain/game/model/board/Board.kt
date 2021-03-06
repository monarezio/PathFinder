package net.zdendukmonarezio.pathfinder.domain.game.model.board

import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field

/**
 * Created by monarezio on 23/04/2017.
 */
interface Board {

    /**
     * returns the position of the first found field
     */
    fun find(field: Field): Coordinate

    /**
     * sets the field on the given position
     */
    fun set(x: Int, y: Int, field: Field): Board

    /**
     * sets the field on the given position
     */
    fun set(coordinate: Coordinate, field: Field): Board

    /**
     * returns the fields as a 2d list
     */
    fun getFields(): List<List<Field>>

    /**
     * returns the field on the coord
     */
    fun getField(coord: Coordinate): Field

    /**
     * returns the amount of rows
     */
    fun getRows(): Int

    /**
     * returns the amount of columns
     */
    fun getColumns(): Int

}