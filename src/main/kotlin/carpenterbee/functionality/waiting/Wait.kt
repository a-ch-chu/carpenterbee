// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration

public class Wait(
    public var timeout: Duration = Default.timeout,
    public var polling: Int = Default.pollCount
) {
    public val interval: Duration
        get() =
            if (polling == 0) Duration.INFINITE
            else (timeout / polling).absoluteValue

    private fun <T> pollFlow(poll: () -> T) = flow {
        while (true) {
            emit(poll())
            delay(interval)
        }
    }

    public suspend fun <T> toGetAsync(
        retrieve: () -> T?,
        condition: (T?) -> Boolean = { it != null }
    ): T? = withTimeoutOrNull(timeout) {
        pollFlow(retrieve)
            .dropWhile { !condition(it) }
            .first()
    }

    public fun <T> toGet(
        retrieve: () -> T?,
        condition: (T?) -> Boolean = { it != null }
    ): T? = runBlocking { toGetAsync(retrieve, condition) }


    public suspend fun untilAsync(condition: () -> Boolean): Boolean =
        withTimeoutOrNull(timeout) {
            pollFlow(condition)
                .dropWhile { !it }
                .first()
        } ?: false

    public fun until(condition: () -> Boolean): Boolean =
        runBlocking { untilAsync(condition) }
}