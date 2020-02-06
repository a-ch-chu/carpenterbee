// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.functionality.waiting.TagFinder

public val HasParent<*>.present get() = !absent
public val HasParent<*>.absent get() = TagFinder.findOrNull(this) == null