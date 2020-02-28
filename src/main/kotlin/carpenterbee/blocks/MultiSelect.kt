// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.controls.options.Option
import carpenterbee.functionality.getBoolAttribute

private typealias MultiOption<TParent, TDefaultTo> = Option<MultiSelect<TParent, TDefaultTo>, TDefaultTo>

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> MultiSelect(parent: TParent, specifier: By) =
    MultiSelect(parent, specifier, ::toParent)

public open class MultiSelect<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    protected val route: TParent.() -> TDefaultTo
) : Section<TParent>(parent, specifier),
    Sequence<MultiOption<TParent, TDefaultTo>> {

    public val isMultiSelect: Boolean get() = tag.getBoolAttribute("multiple")

    public override fun iterator(): Iterator<MultiOption<TParent, TDefaultTo>> =
        getElements(::Option, this, by.tag("option")) {
            parent.route()
        }.iterator()
}