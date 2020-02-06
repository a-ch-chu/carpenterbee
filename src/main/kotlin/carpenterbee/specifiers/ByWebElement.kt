// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.specifiers

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public class ByWebElement(private val element: WebElement, private vararg val moreElements: WebElement) : By() {
    public override fun findElement(context: SearchContext?) = element

    public override fun findElements(context: SearchContext?) = mutableListOf(element, *moreElements)

    public override fun toString(): String = "By.webElements $element and ${moreElements.size} others"
}