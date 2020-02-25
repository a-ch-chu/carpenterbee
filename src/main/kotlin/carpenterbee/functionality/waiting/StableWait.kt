// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration

public class StableWait(
    public var resolution: Duration = Default.interval,
    public var sampleCount: Int = Default.pollCount,
    public var stability: Int = 2
) {
    public val timeout: Duration get() = resolution * sampleCount

    private fun <T> windowFlow(sample: () -> T) = flow {
        val samples = mutableListOf<T>()
        while (true) {
            samples.add(sample())
            if (samples.size >= stability) {
                emit(samples)
                samples.drop(1)
                delay(resolution)
            }
        }
    }

    public suspend fun <T> toGetAsync(
        sample: () -> T?,
        condition: Iterable<T?>.() -> Boolean = { all { it != null } }
    ): T? =
        withTimeoutOrNull(timeout) {
            windowFlow(sample)
                .dropWhile { !condition(it) }
                .first().last()
        }

    public fun <T> toGet(
        sample: () -> T?,
        condition: Iterable<T?>.() -> Boolean = { all { it != null } }
    ): T? = runBlocking { toGetAsync(sample, condition) }


    public suspend fun <T> untilAsync(
        sample: () -> T?,
        condition: Iterable<T?>.() -> Boolean = { all { it != null } }
    ): Boolean = toGetAsync(sample, condition) != null

    public fun <T> until(
        sample: () -> T?,
        condition: Iterable<T?>.() -> Boolean = { all { it != null } }
    ): Boolean = toGet(sample, condition) != null
}