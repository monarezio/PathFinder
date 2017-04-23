package net.zdendukmonarezio.pathfinder.domain.common.extensions

import com.google.gson.GsonBuilder

/**
 * Created by monarezio on 22/04/2017.
 */

fun main(args: Array<String>) {
    /*val gson = GsonBuilder().create()
    val json = "{ \"0\" : { \"north\": -1, \"east\": -1, \"south\": -1, \"west\": 1 }, \"1\" : { \"north\": -1, \"east\": 0, \"south\": -1, \"west\": -1 }, \"2\" : { \"north\": -1, \"east\": -1, \"south\": 1, \"west\": -1 } }"
    val map = gson.fromJson(json, Object::class.java) as Map<String, Map<String, Int>>
    println(Point.createFromJson(map))*/

    var startBuilder = PointBuilder.create()
    var secondBuilder = PointBuilder.create().setNorth(startBuilder)
    startBuilder = secondBuilder.setSouth(secondBuilder)
    var thirdBuilder = PointBuilder.create().setWest(startBuilder)
    startBuilder = startBuilder.setEast(PointBuilder.create())

    println(startBuilder.build())
}