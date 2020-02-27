// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import kotlin.time.Duration
import kotlin.time.seconds

public object Default {
    public const val pollCount: Int = 10
    public const val stabilityFactor: Int = 2
    public const val timeoutSeconds: Int = 3
    public val interval: Duration get() = timeout / pollCount
    public val timeout: Duration get() = timeoutSeconds.seconds
}