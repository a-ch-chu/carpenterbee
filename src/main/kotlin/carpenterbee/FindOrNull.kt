// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public fun SearchContext?.findOrNull(specifier: By): WebElement? =
    this?.findElements(specifier)?.firstOrNull()