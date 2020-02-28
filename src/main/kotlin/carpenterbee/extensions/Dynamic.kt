// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.*

public fun <TElement : Element, TParent : Block> TParent.dynamic(
    constructor: (TParent) -> TElement
): TElement = constructor(this)

public fun <TElement : Element, TParent : Block> TParent.dynamic(
    constructor: (TParent, By) -> TElement,
    specifier: By
): TElement = constructor(this, specifier)

public fun <TElement : Element, TParent : Block, TDefaultTo : Block> TParent.dynamic(
    constructor: (TParent, By, TParent.() -> TDefaultTo) -> TElement,
    specifier: By,
    route: TParent.() -> TDefaultTo
): TElement = constructor(this, specifier, route)