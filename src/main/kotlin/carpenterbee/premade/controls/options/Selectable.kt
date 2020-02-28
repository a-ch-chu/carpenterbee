// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.premade.controls.options

import carpenterbee.*

public fun <TDefaultTo : Block> Sequence<Selectable<*, TDefaultTo>>.setAll(
    selected: Boolean
): TDefaultTo = map { it.setTo(selected) }.last()

public fun <TParent : Block, TRouteTo : Block> Sequence<Selectable<TParent, *>>.setAll(
    selected: Boolean,
    route: (TParent) -> TRouteTo
) = map { it.setTo(selected, route) }.last()

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Selectable(parent: TParent, specifier: By) =
    Selectable(parent, specifier, ::toParent)

public open class Selectable<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Option<TParent, TDefaultTo>(parent, specifier, route) {
    public fun deselect() = select(route)
    public fun <TRouteTo : Block> deselect(route: (TParent) -> TRouteTo) =
        setTo(false, route)

    public fun setTo(desired: Boolean) = setTo(desired, route)
    public fun <TRouteTo : Block> setTo(desired: Boolean, route: (TParent) -> TRouteTo) =
        clickIf(desired xor selected, route)
}