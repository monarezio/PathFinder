package net.zdendukmonarezio.pathfinder.domain.common.extensions

/**
 * Created by monarezio on 23/04/2017.
 */

fun<E> List<E>.set(index: Int, value: E): List<E> {
    return mapIndexed { i, e ->
        if(i == index) value
        else e
    }
}