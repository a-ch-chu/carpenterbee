// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import org.openqa.selenium.By
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public object MultiTagFinder {
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
            ?: scope?.findElements(specifier)?.asSequence()
            ?: emptySequence()
}