package net.zdendukmonarezio.pathfinder.domain.game.model.utils

/**
 * Created by monarezio on 27/05/2017.
 */
data class Path private constructor(val coordinates: List<Coordinate>): Comparable<Path> {

    fun getSize() = coordinates.size

    fun isEmpty() = coordinates.isEmpty()

    operator fun plus(b: Coordinate): Path = Path.create(coordinates + b)

    operator fun plus(b: Path): Path = Path.create(coordinates + b.coordinates)

    override fun compareTo(other: Path): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun createEmpty() = Path(listOf())

        fun create(coordinates: List<Coordinate>) = Path(coordinates)
    }
}