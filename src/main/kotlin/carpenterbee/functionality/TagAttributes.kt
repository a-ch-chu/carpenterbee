// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.WebElement

public fun WebElement.getDataAttribute(name: String): String? =
    getAttribute("data-$name")

public val WebElement.htmlClasses: Sequence<String>
    get() =
        (getAttribute("class") ?: "")
            .split(' ')
            .filterNot(String::isBlank)
            .asSequence()

public val WebElement.id: String?
    get() = getAttribute("id")

public val WebElement.name: String?
    get() = getAttribute("name")

public val WebElement.onClick: String?
    get() = getAttribute("onclick")

public val WebElement.style: String?
    get() = getAttribute("style")

public val WebElement.title: String?
    get() = getAttribute("title")

public val WebElement.value: String?
    get() = getAttribute("value")