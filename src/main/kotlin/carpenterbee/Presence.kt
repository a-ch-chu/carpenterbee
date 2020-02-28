// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.functionality.HasParent
import carpenterbee.functionality.TagFinder

public val HasParent<*>.present get() = !absent
public val HasParent<*>.absent
    get() = TagFinder.findOrNull(this) == null