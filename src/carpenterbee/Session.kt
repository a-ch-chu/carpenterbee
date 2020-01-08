// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import org.openqa.selenium.WebDriver

public class Session(val driver: WebDriver) {
    public constructor(browser: () -> WebDriver) : this(browser())

    public var lastPage: Page? = null; private set
    public var lastBlock: Block? = null; private set
    public var lastControl: Control<*, *>? = null; private set

    public fun track(element: Element) {
        if (element is Page) lastPage = element
        if (element is Block) lastBlock = element
        if (element is Control<*, *>) lastControl = element
    }

    public inline fun <reified TElement : Element> last(): TElement =
        lastOrNull() ?: throw IllegalStateException("Couldn't find any recent element of the given type.")

    public inline fun <reified TElement : Element> lastOrNull(): TElement? = when {
        lastControl is TElement -> lastControl as TElement
        lastBlock is TElement -> lastBlock as TElement
        lastPage is TElement -> lastPage as TElement
        else -> null
    }

    public fun <TPage : Page, TNavigator : Page.Navigator<TPage>> navigateTo(
        navigator: TNavigator,
        getUrl: TNavigator.() -> String
    ) = navigateTo(navigator, navigator.getUrl())

    public fun <TPage : Page> navigateTo(navigator: Page.Navigator<TPage>, url: String) =
        navigateTo(navigator.constructor, url)

    public fun <TPage : Page> navigateTo(constructor: (Session) -> TPage, url: String): TPage {
        driver.navigate().to(url)
        return constructor(this)
    }
}