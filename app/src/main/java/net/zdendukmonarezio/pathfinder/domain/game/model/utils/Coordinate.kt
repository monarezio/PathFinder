package net.zdendukmonarezio.pathfinder.domain.game.model.utils

/**
 * Created by monarezio on 23/04/2017.
 */
data class Coordinate(val x: Int, val y: Int) {

    fun north(): Coordinate = Coordinate(x, y - 1)

    fun south(): Coordinate = Coordinate(x, y + 1)

    fun east(): Coordinate = Coordinate(x + 1, y)

    fun west(): Coordinate = Coordinate(x - 1, y)
}