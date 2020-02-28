// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.*

public fun SearchContext?.getTagOrNull(specifier: By): WebElement? =
    this?.run { findElements(specifier).firstOrNull() }

public fun HasParent<*>.getTagOrNull(): WebElement? =
    parent.scope.getTagOrNull(specifier)