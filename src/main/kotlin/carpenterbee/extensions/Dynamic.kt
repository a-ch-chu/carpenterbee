// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.*

public fun <TElement : Element, TParent : Block> TParent.dynamic(
    constructor: (TParent) -> TElement
) = constructor(this)

public fun <TElement : Element, TParent : Block> TParent.dynamic(
    constructor: (TParent, By) -> TElement,
    specifier: By
) = constructor(this, specifier)

public fun <TElement : Element, TParent : Block, TDefaultRoute : Block> TParent.dynamic(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TElement,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) = constructor(this, specifier, route)

public fun <
        TControl : Control<TParent, TDefaultRoute>,
        TParent : Block, TDefaultRoute : Block
        > TParent.dynamic(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TControl,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) = constructor(this, specifier, route)