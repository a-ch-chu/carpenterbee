// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.controls.options.Option
import carpenterbee.functionality.getBoolAttribute

private typealias MultiOption<TParent, TDefaultRoute> = Option<MultiSelect<TParent, TDefaultRoute>, TDefaultRoute>

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> MultiSelect(parent: TParent, specifier: By) =
    MultiSelect(parent, specifier, ::toParent)

public open class MultiSelect<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    protected val route: (TParent) -> TDefaultRoute
) : Section<TParent>(parent, specifier),
    Sequence<MultiOption<TParent, TDefaultRoute>> {

    public val isMultiSelect: Boolean get() = tag.getBoolAttribute("multiple")

    public override fun iterator(): Iterator<MultiOption<TParent, TDefaultRoute>> =
        getElements(::Option, this, by.tag("option")) {
            route(it.parent)
        }.iterator()
}