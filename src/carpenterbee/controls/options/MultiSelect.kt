// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.*
import carpenterbee.getElements
import org.openqa.selenium.By

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> MultiSelect(parent: TParent, specifier: By) =
    MultiSelect(parent, specifier, { it })

public open class MultiSelect<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Control<TParent, TDefaultRoute>(parent, specifier, route), Sequence<Option<TParent, TDefaultRoute>> {
    public val isMultiSelect get() = read { getAttribute("multiple") } != null

    public override fun iterator(): Iterator<Option<TParent, TDefaultRoute>> =
        getElements(::Option, parent, by.tag("option"), route).iterator()
}