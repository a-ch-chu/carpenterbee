// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public object MultiTagFinder {
    public fun findSome(
        scope: SearchContext?,
        specifier: By,
        wait: Wait = Wait().apply { timeout /= 2 }.apply { polling /= 2 }
    ): Sequence<WebElement> =
        (wait.toGet(
            { scope?.findElements(specifier) },
            { it?.size ?: 0 > 0 }) ?: mutableListOf<WebElement>())
            .asSequence()

    public fun findStable(
        scope: SearchContext?,
        specifier: By,
        wait: StableWait = StableWait()
    ): Sequence<WebElement> =
        (wait.toGet(
            { scope?.findElements(specifier) },
            { map { it?.size ?: 0 }.distinct().size == 1 }
        ) ?: scope?.findElements(specifier))
            ?.asSequence() ?: emptySequence()
}