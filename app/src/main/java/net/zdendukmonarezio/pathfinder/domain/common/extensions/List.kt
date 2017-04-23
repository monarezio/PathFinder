package net.zdendukmonarezio.pathfinder.domain.common.extensions

import net.zdendukmonarezio.pathfinder.domain.game.model.utils.Coordinate

/**
 * Created by monarezio on 23/04/2017.
 */

fun<E> List<E>.set(index: Int, value: E): List<E> {
    return mapIndexed { i, e ->
        if(i == index) value
        else e
    }
}

fun<E> List<List<E>>.isInBounds(x: Int, y: Int): Boolean = x >= 0 && x < size && y >= 0 && y < get(x).size

fun<E> List<List<E>>.isInBounds(coordinate: Coordinate): Boolean = isInBounds(coordinate.x, coordinate.y)