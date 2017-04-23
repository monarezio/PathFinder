package net.zdendukmonarezio.pathfinder.domain.common.extensions

import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by monarezio on 22/04/2017.
 */

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}