// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import kotlin.time.Duration
import kotlin.time.seconds

public object Default {
    public const val timeoutSeconds = 3
    public val timeout: Duration get() = timeoutSeconds.seconds
    public const val pollCount: Int = 10
}