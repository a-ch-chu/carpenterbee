// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import carpenterbee.functionality.getOrNull
import carpenterbee.functionality.HasFindTimeout
import carpenterbee.functionality.HasParent
import org.openqa.selenium.NotFoundException
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
}