// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.specifiers

import carpenterbee.*

public class ByWebElement(private val element: WebElement, private vararg val moreElements: WebElement) : By() {
    public override fun findElement(context: SearchContext?) = element

    public override fun findElements(context: SearchContext?) = mutableListOf(element, *moreElements)

    public override fun toString(): String = "By.webElements $element and ${moreElements.size} others"
}