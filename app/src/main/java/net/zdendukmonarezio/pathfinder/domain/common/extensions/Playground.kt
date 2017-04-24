package net.zdendukmonarezio.pathfinder.domain.common.extensions

import com.google.gson.GsonBuilder
import net.zdendukmonarezio.pathfinder.domain.game.model.board.GameBoard

/**
 * Created by monarezio on 22/04/2017.
 */

fun main(args: Array<String>) {
    val json = "{ \"fields\": [ [\"SOLID\", \"SOLID\", \"SOLID\", \"SOLID\"], [\"FINISH\", \"AIR\", \"PLAYER\", \"AIR\"], [\"SOLID\", \"SOLID\", \"SOLID\", \"SOLID\"], [\"SOLID\", \"SOLID\", \"SOLID\", \"SOLID\"] ] }"
    val gson = GsonBuilder().create()

    println(gson.fromJson(json, GameBoard::class.java))
}