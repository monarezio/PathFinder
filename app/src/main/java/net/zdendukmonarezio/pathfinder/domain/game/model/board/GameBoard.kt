package net.zdendukmonarezio.pathfinder.domain.game.model.board

import net.zdendukmonarezio.pathfinder.domain.common.extensions.set
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field

/**
 * Created by monarezio on 23/04/2017.
 */
data class GameBoard private constructor(private val fields: List<List<Field>>) : Board{

    override fun find(field: Field): Coordinate {
        val y = fields.indexOfFirst { i -> i.contains(Field.PLAYER) }
        val x = fields[y].indexOfFirst { i -> i == Field.PLAYER }
        return Coordinate(x, y)
    }

    override fun set(x: Int, y: Int, field: Field): Board = GameBoard(fields.set(x, fields[x].set(y, field)))

    override fun set(coordinate: Coordinate, field: Field): Board = set(coordinate.x, coordinate.y, field)

    override fun getFields(): List<List<Field>> = fields

    companion object {
        fun createBoard(fields: List<List<Field>>): Board = GameBoard(fields)
    }
}