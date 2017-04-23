package net.zdendukmonarezio.pathfinder.domain.game.model.board

import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate
import org.junit.Test

import org.junit.Assert.*
import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Field;

/**
 * Created by monarezio on 23/04/2017.
 */
class GameBoardTest {
    val gameBoard = GameBoard.createBoard(listOf(
            listOf(Field.SOLID, Field.SOLID, Field.SOLID, Field.SOLID),
            listOf(Field.PLAYER, Field.SOLID, Field.SOLID, Field.SOLID),
            listOf(Field.SOLID, Field.SOLID, Field.SOLID, Field.SOLID),
            listOf(Field.SOLID, Field.SOLID, Field.SOLID, Field.SOLID)
    ))

    @Test
    fun find() {
        assertEquals(Coordinate(0, 1), gameBoard.find(Field.PLAYER))
    }

    @Test
    fun set() {
        val newGb = gameBoard.set(0, 2, Field.AIR)
        assertEquals(Field.AIR, newGb.getFields()[0][2])
    }

}