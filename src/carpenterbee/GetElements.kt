// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.functionality.waiting.MultiTagFinder
import carpenterbee.functionality.waiting.StableWait
import carpenterbee.specifiers.ByWebElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

private fun <T> Block.mapChildren(specifier: By, wait: StableWait, mapping: (WebElement) -> T) =
    MultiTagFinder.findStable(scope, specifier, wait).map(mapping)

public fun <TElement : Element, TParent : Block> getElements(
    constructor: (TParent, By) -> TElement,
    parent: TParent,
    specifier: By,
    wait: StableWait = StableWait()
) = parent.mapChildren(specifier, wait) { constructor(parent, ByWebElement(it)) }

public fun <TControl : Control<TParent, TDefaultRoute>, TParent : Block, TDefaultRoute : Block> getElements(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TControl,
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute,
    wait: StableWait = StableWait()
) = parent.mapChildren(specifier, wait) { constructor(parent, ByWebElement(it), route) }