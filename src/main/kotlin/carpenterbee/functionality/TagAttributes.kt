// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import org.openqa.selenium.WebElement

public fun WebElement.getDataAttribute(name: String): String? =
    getAttribute("data-$name")

public val WebElement.htmlClasses: Sequence<String>
    get() =
        (getAttribute("class") ?: "")
            .split(' ')
            .filter { !it.isBlank() }
            .asSequence()

public val WebElement.id: String?
    get() = getAttribute("id")

public val WebElement.onClick: String?
    get() = getAttribute("onclick")

public val WebElement.style: String?
    get() = getAttribute("style")

public val WebElement.title: String?
    get() = getAttribute("title")

public val WebElement.value: String?
    get() = getAttribute("value")