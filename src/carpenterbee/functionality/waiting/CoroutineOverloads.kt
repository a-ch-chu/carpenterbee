// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import kotlinx.coroutines.CoroutineScope
import kotlin.time.Duration

public suspend fun delay(time: Duration): Unit = kotlinx.coroutines.delay(time.toLongMilliseconds())

public suspend fun <T> withTimeoutOrNull(time: Duration, block: suspend CoroutineScope.() -> T): T? =
    kotlinx.coroutines.withTimeoutOrNull(time.toLongMilliseconds(), block)