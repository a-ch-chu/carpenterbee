// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.*
import carpenterbee.functionality.interfaces.HasFindTimeout
import carpenterbee.functionality.interfaces.IsFindable
import carpenterbee.functionality.waiting.StableWait
import carpenterbee.functionality.waiting.Wait
import org.openqa.selenium.NotFoundException

public object TagFinder {
    public fun <T> findOrNull(element: HasFindTimeout, fetch: () -> T?): T? =
        Wait(element.findTimeout).toGet(fetch)

    public fun <T> find(element: HasFindTimeout, fetch: () -> T?): T =
        findOrNull(element, fetch)
            ?: throw NotFoundException(
                element.run { "Couldn't find $name after $findTimeout" }
            )

    public fun findOrNull(element: IsFindable<*>): WebElement? =
        findOrNull(element) { element.getTagOrNull() }

    public fun find(element: IsFindable<*>): WebElement =
        findOrNull(element)
            ?: throw NotFoundException(
                element.run { "Couldn't find $name $specifier after $findTimeout." }
            )

    public fun findSome(
        scope: SearchContext?,
        specifier: By,
        wait: Wait = Wait()
    ): Sequence<WebElement> =
        wait.toGet(
            { scope.getTags(specifier) },
            { it!!.count() > 0 }
        ) ?: throw NotFoundException(
            "Couldn't find any elements $specifier after ${wait.timeout}."
        )

    public fun findStable(
        scope: SearchContext?,
        specifier: By,
        wait: StableWait = StableWait()
    ): Sequence<WebElement> =
        wait.toGet(
            { scope.getTags(specifier) },
            { map { it!!.count() }.distinct().size == 1 }
        ) ?: throw NotFoundException(
            "Couldn't find stable set of elements $specifier after ${wait.timeout}."
        )
}