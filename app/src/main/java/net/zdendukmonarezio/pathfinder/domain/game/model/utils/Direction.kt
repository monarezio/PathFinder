package net.zdendukmonarezio.pathfinder.domain.game.model.utils

/**
 * Created by monarezio on 23/04/2017.
 */
enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    companion object {
        fun getHorizontal(): Set<Direction> = setOf(EAST, WEST)

        fun getVertical(): Set<Direction> = setOf(NORTH, SOUTH)
    }
}