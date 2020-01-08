// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import carpenterbee.functionality.interfaces.HasParent
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.FluentWait
import kotlin.time.Duration
import kotlin.time.toJavaDuration

public object ElementFinder {
    private val waiter by lazy { FluentWait(Any()).ignoring(NotFoundException::class.java) }

    public fun <T> waitToGet(timeout: Duration, fetch: () -> T?): T =
        waiter
            .withTimeout(timeout.toJavaDuration())
            .pollingEvery((timeout / Default.pollCount).toJavaDuration())
            .until { fetch() }!!

    public fun waitToFind(element: HasParent<*>): WebElement =
        waitToFindOrNull(element)
            ?: throw NotFoundException("Couldn't find ${element::class.simpleName} after ${element.findTimeout}.")

    public fun waitToFindOrNull(element: HasParent<*>): WebElement? =
        waitToGet(element.findTimeout)
        { element.parent.scope.findElements(element.specifier).firstOrNull() }
}