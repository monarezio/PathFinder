package net.zdendukmonarezio.pathfinder.domain.mazes.models

import net.zdendukmonarezio.pathfinder.domain.common.extensions.set

/**
 * Created by monarezio on 23/04/2017.
 */
data class MazeListBuilder(val list: List<MazeBuilder>) {

    fun setFinished(fileName: String): MazeListBuilder {
        return MazeListBuilder(list.map { i ->
            if(i.fileName == fileName)
                i.setFinished(true)
            else
                i
        })
    }

    fun setFinished(fileNames: Set<String>): MazeListBuilder {
        return MazeListBuilder(list.map { i ->
            if(fileNames.contains(i.fileName))
                i.setFinished(true)
            else
                i
        })
    }

    fun build(): MazeList = MazeList(list.map { i -> i.build() })

}