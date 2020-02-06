// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.Block
import carpenterbee.Section
import carpenterbee.controls.options.Option
import carpenterbee.getElements
import org.openqa.selenium.By

private typealias MultiOption<TParent, TDefaultRoute> = Option<MultiSelect<TParent, TDefaultRoute>, TDefaultRoute>

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> MultiSelect(parent: TParent, specifier: By) =
    MultiSelect(parent, specifier, { it })

public open class MultiSelect<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    protected val route: (TParent) -> TDefaultRoute
) : Section<TParent>(parent, specifier), Sequence<MultiOption<TParent, TDefaultRoute>> {
    public val isMultiSelect get() = tag.getAttribute("multiple") != null

    public override fun iterator(): Iterator<MultiOption<TParent, TDefaultRoute>> =
        getElements(::Option, this, by.tag("option"), { route(it.parent) }).iterator()
}