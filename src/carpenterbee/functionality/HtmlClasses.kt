// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import org.openqa.selenium.WebElement

public val WebElement.htmlClasses: Sequence<String>
    get() =
        getAttribute(Tag.htmlClass)
            .split(' ')
            .filter { !it.isBlank() }
            .asSequence()

public fun WebElement.hasHtmlClass(className: String): Boolean =
    htmlClasses.contains(className)