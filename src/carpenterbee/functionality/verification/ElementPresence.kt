// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.verification

import carpenterbee.functionality.interfaces.HasParent
import carpenterbee.functionality.waiting.ElementFinder

public val HasParent<*>.present get() = !absent
public val HasParent<*>.absent get() = ElementFinder.waitToFindOrNull(this) == null