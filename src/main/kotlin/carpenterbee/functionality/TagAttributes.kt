// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import org.openqa.selenium.WebElement

public object Tag {
    public const val htmlClass = "class"
    public const val id = "id"
    public const val style = "style"
    public const val title = "title"
    public const val value = "value"
}

public val WebElement.htmlClasses: Sequence<String>
    get() =
        (getAttribute(Tag.htmlClass) ?: "")
            .split(' ')
            .filter { !it.isBlank() }
            .asSequence()

public fun WebElement.hasHtmlClass(className: String): Boolean =
    htmlClasses.contains(className)