package net.zdendukmonarezio.pathfinder.domain.game.model.utils

/**
 * Created by monarezio on 27/05/2017.
 */
data class Path private constructor(val coordinates: List<Coordinate>): Comparable<Path> {

    fun getSize() = coordinates.size

    fun isEmpty() = coordinates.isEmpty()

    fun removeLast(): Path = create(coordinates.dropLast(1))

    fun first(): Coordinate = coordinates.first()

    fun last() = coordinates.last()

    operator fun minus(b: Path): Path = Path.create(coordinates - b.coordinates)

    operator fun plus(b: Coordinate): Path = Path.create(coordinates + b)

    operator fun plus(b: Path): Path = Path.create(coordinates + b.coordinates)

    override fun compareTo(other: Path): Int {
        if(other.isEmpty())
            return -1
        return getSize() - other.getSize()
    }

    companion object {
        fun createEmpty() = Path(listOf())

        fun create(coordinates: List<Coordinate>) = Path(coordinates)
    }
}