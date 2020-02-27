// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.*

public fun <TDefaultRoute : Block> Sequence<Response<*, TDefaultRoute>>.setAll(selected: Boolean) =
    map { it.setTo(selected) }.last()

public fun <TParent : Block, TRouteTo : Block> Sequence<Response<TParent, *>>.setAll(
    selected: Boolean,
    route: (TParent) -> TRouteTo
) = map { it.setTo(selected, route) }.last()

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Response(parent: TParent, specifier: By) =
    Response(parent, specifier, ::i)

public open class Response<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Option<TParent, TDefaultRoute>(parent, specifier, route) {
    public fun deselect() = select(route)
    public fun <TRouteTo : Block> deselect(route: (TParent) -> TRouteTo) = setTo(false, route)

    public fun setTo(desired: Boolean) = setTo(desired, route)
    public fun <TRouteTo : Block> setTo(desired: Boolean, route: (TParent) -> TRouteTo) =
        clickIf(desired xor selected, route)
}