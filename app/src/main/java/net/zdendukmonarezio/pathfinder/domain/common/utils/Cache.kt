package net.zdendukmonarezio.pathfinder.domain.common.utils

/**
 * Created by monarezio on 22/04/2017.
 */
class Cache<E> private constructor() {
    private var cachedItems: Map<Int, E> = mapOf()

    /**
     * returns the key if it exists, if it does not exist it adds to cache and returns it
     */
    fun getItem(key: Int, item: E): E {
        if(!cachedItems.containsKey(key))
            cachedItems = cachedItems + mapOf<Int, E>(Pair(key, item))
        return item
    }

    fun getItem(key: Int): E? = cachedItems[key]


    /**
     * deletes the current cache
     */
    fun flush(): Unit {
        cachedItems = mapOf()
    }

    companion object {
        fun<E> createCache() = Cache<E>()
    }
}