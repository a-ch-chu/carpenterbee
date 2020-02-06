// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.Block
import carpenterbee.Control
import carpenterbee.Element
import org.openqa.selenium.By

public fun <TElement : Element, TParent : Block> TParent.dynamic(
    constructor: (TParent, By) -> TElement,
    specifier: By
) = constructor(this, specifier)

public fun <TElement : Element, TParent : Block, TDefaultRoute : Block> TParent.dynamic(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TElement,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) = constructor(this, specifier, route)

public fun <TControl : Control<TParent, TDefaultRoute>, TParent : Block, TDefaultRoute : Block> TParent.dynamic(
    constructor: (TParent, By, (TParent) -> TDefaultRoute) -> TControl,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) = constructor(this, specifier, route)