// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.specifiers.ByWebElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

private fun <T> Block.mapChildren(specifier: By, mapping: (WebElement) -> T) =
    this.scope.findElements(specifier).asSequence().map(mapping)

public fun <TElement : Element, TParent : Block> getElements(
    constructor: (TParent, By) -> TElement,
    parent: TParent,
    specifier: By
) = parent.mapChildren(specifier) { constructor(parent, ByWebElement(it)) }

public fun <TControl : Control<TParent, TDefaultRoute>, TParent : Block, TDefaultRoute : Block> getElements(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TControl,
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) = parent.mapChildren(specifier) { constructor(parent, ByWebElement(it), route) }