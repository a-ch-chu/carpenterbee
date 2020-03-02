// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.*

public fun SearchContext?.getTagOrNull(specifier: By): WebElement? =
    this?.run { findElements(specifier).firstOrNull() }

public fun IsFindable<*>.getTagOrNull(): WebElement? =
    parent.scope.getTagOrNull(specifier)

public fun SearchContext?.getTags(specifier: By): Sequence<WebElement> =
    this?.findElements(specifier)?.asSequence() ?: emptySequence()