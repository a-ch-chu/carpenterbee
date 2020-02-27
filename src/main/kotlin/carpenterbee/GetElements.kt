// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.functionality.TagFinder
import carpenterbee.functionality.waiting.StableWait
import carpenterbee.functionality.specifiers.ByWebElement

private fun <T> Block.mapChildren(
    specifier: By,
    wait: StableWait,
    mapping: (WebElement) -> T
) = TagFinder.findStable(scope, specifier, wait).map(mapping)

public fun <TElement : Element, TParent : Block> getElements(
    constructor: (TParent, By) -> TElement,
    parent: TParent,
    specifier: By,
    wait: StableWait = StableWait()
): Sequence<TElement> = parent.mapChildren(specifier, wait) {
    constructor(parent, ByWebElement(it))
}

public fun <TControl : Control<TParent, TDefaultRoute>,
        TParent : Block,
        TDefaultRoute : Block>
        getElements(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TControl,
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
): Sequence<TControl> =
    getElements(constructor, parent, specifier, StableWait(), route)

public fun <TControl : Control<TParent, TDefaultRoute>,
        TParent : Block,
        TDefaultRoute : Block>
        getElements(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TControl,
    parent: TParent,
    specifier: By,
    wait: StableWait,
    route: (TParent) -> TDefaultRoute
): Sequence<TControl> = parent.mapChildren(specifier, wait) {
    constructor(parent, ByWebElement(it), route)
}