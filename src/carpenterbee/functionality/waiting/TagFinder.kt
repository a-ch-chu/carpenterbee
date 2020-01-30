// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import carpenterbee.findOrNull
import carpenterbee.functionality.interfaces.HasFindTimeout
import carpenterbee.functionality.interfaces.HasParent
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.WebElement

public object TagFinder {
    public fun <T> findOrNull(element: HasFindTimeout, fetch: () -> T?): T? =
        Wait(element.findTimeout).toGet(fetch)

    public fun <T> find(element: HasFindTimeout, fetch: () -> T?): T =
        findOrNull(element, fetch)
            ?: throw NotFoundException("Couldn't find ${element::class.simpleName} after ${element.findTimeout}.")


    private fun HasParent<*>.getOrNull() = parent.scope.findOrNull(specifier)

    public fun findOrNull(element: HasParent<*>): WebElement? =
        findOrNull(element) { element.getOrNull() }

    public fun find(element: HasParent<*>): WebElement =
        find(element) { element.getOrNull() }
}