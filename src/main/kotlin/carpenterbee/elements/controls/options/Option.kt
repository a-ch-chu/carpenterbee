// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.elements.controls.options

import carpenterbee.*
import carpenterbee.elements.HasValue
import carpenterbee.elements.controls.Button

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Option(parent: TParent, specifier: By) =
    Option(parent, specifier, ::toParent)

public open class Option<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Button<TParent, TDefaultTo>(parent, specifier, route),
    HasValue {
    public val selected: Boolean get() = read { selected }
    public override val value: String get() = read(Tag.value)

    protected fun <TRouteTo : Block> clickIf(
        condition: Boolean,
        route: (TParent) -> TRouteTo
    ) = if (condition) interact(route) { click() } else route(parent)

    public fun select() = select(route)
    public fun <TRouteTo : Block> select(route: (TParent) -> TRouteTo) =
        clickIf(!selected, route)
}