// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

public object Tag {
    val id get() = "id"
    val name get() = "name"
    val onClick get() = "onClick"
    val title get() = "title"
    val value get() = "value"
    fun data(name: String) = "data-$name"
}

public val WebElement.classes: Sequence<String>
    get() =
        (getAttrOrNull("class") ?: "")
            .split(' ')
            .filterNot(String::isBlank)
            .asSequence()

public fun WebElement.getAttr(name: String): String =
    getAttrOrNull(name)
        ?: throw UnsupportedOperationException(
            "Tried to retrieve nonexistent WebElement attribute $name"
        )

public fun WebElement.getBoolAttr(name: String): Boolean =
    getAttrOrNull(name) == "true"

public fun WebElement.hasAttr(name: String): Boolean =
    getAttrOrNull(name) != null

public fun WebElement.getAttrOrNull(name: String): String? =
    getAttribute(name)
