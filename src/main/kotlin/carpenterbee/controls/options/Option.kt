// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.*
import carpenterbee.controls.Button
import carpenterbee.controls.traits.HasValue
import carpenterbee.controls.traits.Selectable
import carpenterbee.functionality.value

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Option(parent: TParent, specifier: By) =
    Option(parent, specifier, ::toParent)

public open class Option<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Button<TParent, TDefaultRoute>(parent, specifier, route), Selectable, HasValue {
    public override val selected: Boolean get() = read { selected }
    public override val value: String get() = read { value!! }

    protected fun <TRouteTo : Block> clickIf(condition: Boolean, route: (TParent) -> TRouteTo) =
        if (condition) interact(route) { click() } else route(parent)

    public fun select() = select(route)
    public fun <TRouteTo : Block> select(route: (TParent) -> TRouteTo) = clickIf(!selected, route)
}