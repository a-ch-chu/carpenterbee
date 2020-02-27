// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.controls.options.Option

private typealias BoxOption<TParent, TDefaultRoute> = Option<SelectBox<TParent, TDefaultRoute>, TDefaultRoute>

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> SelectBox(parent: TParent, specifier: By) =
    SelectBox(parent, specifier, ::i)

public open class SelectBox<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    protected val route: (TParent) -> TDefaultRoute
) : Section<TParent>(parent, specifier), Sequence<BoxOption<TParent, TDefaultRoute>> {
    public val selectedOption get() = single { it.selected }

    public override fun iterator(): Iterator<BoxOption<TParent, TDefaultRoute>> =
        getElements(::Option, this, by.tag("option"), { route(it.parent) }).iterator()
}