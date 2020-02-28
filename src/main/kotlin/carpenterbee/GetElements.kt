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

public fun <TControl : Element, TParent : Block, TDefaultTo : Block> getElements(
    constructor: (TParent, By, TParent.() -> TDefaultTo) -> TControl,
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
): Sequence<TControl> =
    getElements(constructor, parent, specifier, StableWait(), route)

public fun <TControl : Element, TParent : Block, TDefaultTo : Block> getElements(
    constructor: (TParent, By, TParent.() -> TDefaultTo) -> TControl,
    parent: TParent,
    specifier: By,
    wait: StableWait,
    route: TParent.() -> TDefaultTo
): Sequence<TControl> = parent.mapChildren(specifier, wait) {
    constructor(parent, ByWebElement(it), route)
}