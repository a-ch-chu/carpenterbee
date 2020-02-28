// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls

import carpenterbee.*

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Button(parent: TParent, specifier: By) =
    Button(parent, specifier, ::toParent)

public open class Button<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Control<TParent, TDefaultTo>(parent, specifier, route) {
    public fun click() = click(route)
    public fun <TRouteTo : Block> click(route: (TParent) -> TRouteTo) =
        interact(route) { click() }
}