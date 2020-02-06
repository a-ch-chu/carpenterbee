// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.functionality.HasParent
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public fun SearchContext?.getOrNull(specifier: By): WebElement? =
    this?.findElements(specifier)?.firstOrNull()

public fun HasParent<*>.getOrNull(): WebElement? =
    parent.scope.getOrNull(specifier)