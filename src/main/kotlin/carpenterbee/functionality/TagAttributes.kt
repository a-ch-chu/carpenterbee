// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.WebElement

public val WebElement.htmlClasses: Sequence<String>
    get() =
        (getAttributeOrNull("class") ?: "")
            .split(' ')
            .filterNot(String::isBlank)
            .asSequence()

public val WebElement.id: String?
    get() = getAttributeOrNull("id")

public val WebElement.name: String?
    get() = getAttributeOrNull("name")

public val WebElement.onClick: String?
    get() = getAttributeOrNull("onclick")

public val WebElement.style: String?
    get() = getAttributeOrNull("style")

public val WebElement.title: String?
    get() = getAttributeOrNull("title")

public val WebElement.value: String?
    get() = getAttributeOrNull("value")


public fun WebElement.getDataAttribute(name: String): String? =
    getAttributeOrNull("data-$name")

public fun WebElement.getBoolAttribute(name: String): Boolean =
    getAttributeOrNull(name) == "true"

public fun WebElement.getAttributeOrThrow(name: String): String =
    getAttributeOrNull(name)
        ?: throw UnsupportedOperationException(
            "Tried to retrieve nonexistent WebElement property $name"
        )

public fun WebElement.hasAttribute(name: String): Boolean =
    getAttributeOrNull(name) != null

public fun WebElement.getAttributeOrNull(name: String): String? =
    getAttribute(name)
