// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import org.openqa.selenium.WebDriver
import java.io.Closeable

public class Session(val driver: WebDriver) : Closeable {
    public constructor(browser: () -> WebDriver) : this(browser())

    public var lastPage: Page? = null; private set
    public var lastBlock: Block? = null; private set
    public var lastElement: Element? = null; private set

    public fun track(element: Element) {
        if (element is Page) lastPage = element
        if (element is Block) lastBlock = element
        lastElement = element
    }

    public inline fun <reified TElement : Element> last(): TElement =
        lastOrNull()
            ?: throw IllegalStateException(
                "Couldn't find any recent element of the given type."
            )

    public inline fun <reified TElement : Element> lastOrNull(): TElement? =
        when {
            lastPage is TElement -> lastPage as TElement
            lastBlock is TElement -> lastBlock as TElement
            lastElement is TElement -> lastElement as TElement
            else -> null
        }

    public fun <TPage : Page, TNavigator : Page.Navigator<TPage>, TArg> navigate(
        action: Nav.(TArg) -> Unit,
        navigator: TNavigator,
        getArg: TNavigator.() -> TArg
    ): TPage = navigate({ action(navigator.getArg()) }, navigator)

    public fun <TPage : Page, TNavigator : Page.Navigator<TPage>> navigate(
        action: Nav.() -> Unit,
        navigator: TNavigator
    ): TPage = navigate(action, navigator.constructor)

    public fun <TPage : Page> navigate(
        action: Nav.() -> Unit,
        constructor: (Session) -> TPage
    ): TPage {
        driver.navigate().action()
        return constructor(this)
    }

    override fun close(): Unit = driver.quit()
}