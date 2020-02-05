// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.specifiers

import org.openqa.selenium.By

public object Specifiers {
    public fun id(id: String): By = By.id(id)

    public fun htmlClass(name: String): By = By.className(name)

    public fun css(selector: String): By = By.cssSelector(selector)

    public fun linkText(text: String, partial: Boolean = false): By =
        if (partial) By.partialLinkText(text)
        else By.linkText(text)

    public fun name(name: String): By = By.name(name)

    public fun tag(type: String): By = By.tagName(type)

    public fun xpath(xpath: String): By = By.xpath(xpath)
}