// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.interfaces

import carpenterbee.functionality.waiting.Default
import kotlin.time.Duration

public interface HasFindTimeout {
    public val findTimeout: Duration get() = Default.timeout
}