package net.zdendukmonarezio.pathfinder.domain.mazes.models

/**
 * Created by monarezio on 23/04/2017.
 */
data class MazeBuilder(val fileName: String, val name: String, val description: String, val finished: Boolean = false) {

    fun setFinished(finished: Boolean) = MazeBuilder(fileName, name, description, finished)

    fun build(): Maze = Maze(fileName, name, description, finished)

    companion object {
        @JvmStatic fun create(fileName: String, name: String, description: String)
            = MazeBuilder(fileName, name, description)
    }
}