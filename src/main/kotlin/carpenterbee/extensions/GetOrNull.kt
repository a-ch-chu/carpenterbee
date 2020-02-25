// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.*
import carpenterbee.functionality.HasParent

public fun SearchContext?.getOrNull(specifier: By): WebElement? =
    this?.run { findElements(specifier).firstOrNull() }

public fun HasParent<*>.getOrNull(): WebElement? =
    parent.scope.getOrNull(specifier)