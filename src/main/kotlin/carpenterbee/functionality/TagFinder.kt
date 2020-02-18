// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.extensions.getOrNull
import carpenterbee.functionality.waiting.StableWait
import carpenterbee.functionality.waiting.Wait
import org.openqa.selenium.By
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public object TagFinder {
    public fun <T> findOrNull(element: HasFindTimeout, fetch: () -> T?): T? =
        Wait(element.findTimeout).toGet(fetch)

    public fun <T> find(element: HasFindTimeout, fetch: () -> T?): T =
        findOrNull(element, fetch)
            ?: throw NotFoundException("Couldn't find ${element::class.simpleName} after ${element.findTimeout}.")

    public fun findOrNull(element: HasParent<*>): WebElement? =
        findOrNull(element) { element.getOrNull() }

    public fun find(element: HasParent<*>): WebElement =
        findOrNull(element)
            ?: throw NotFoundException(
                "Couldn't find ${element::class.simpleName} ${element.specifier} after ${element.findTimeout}."
            )

    public fun findSome(
        scope: SearchContext?,
        specifier: By,
        wait: Wait = Wait()
    ): Sequence<WebElement> =
        wait.toGet(
            { scope?.findElements(specifier) },
            { it?.size ?: 0 > 0 })?.asSequence()
            ?: throw NotFoundException("Couldn't find any elements $specifier after ${wait.timeout}.")

    public fun findStable(
        scope: SearchContext?,
        specifier: By,
        wait: StableWait = StableWait()
    ): Sequence<WebElement> =
        wait.toGet(
            { scope?.findElements(specifier) },
            { map { it?.size ?: 0 }.distinct().size == 1 }
        )?.asSequence()
            ?: throw NotFoundException("Couldn't find stable set of elements $specifier after ${wait.timeout}.")
}