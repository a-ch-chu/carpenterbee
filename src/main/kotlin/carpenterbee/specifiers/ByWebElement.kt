// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.specifiers

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public class ByWebElement(private val element: WebElement, private vararg val elements: WebElement) : By() {
    public override fun findElement(context: SearchContext?) = element

    public override fun findElements(context: SearchContext?) = mutableListOf(element, *elements)

    public override fun toString(): String = "By.webElements $element and ${elements.size} others"
}