// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.elements.sections

import carpenterbee.*
import carpenterbee.elements.controls.options.Option

private typealias BoxOption<TParent, TDefaultTo> =
        Option<SelectBox<TParent, TDefaultTo>, TDefaultTo>

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> SelectBox(parent: TParent, specifier: By) =
    SelectBox(parent, specifier, ::toParent)

public open class SelectBox<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    protected val route: TParent.() -> TDefaultTo
) : Section<TParent>(parent, specifier),
    Sequence<BoxOption<TParent, TDefaultTo>> {

    public val selectedOption: BoxOption<TParent, TDefaultTo>
        get() = single { it.selected }

    public override fun iterator(): Iterator<BoxOption<TParent, TDefaultTo>> =
        getElements(::Option, this, by.tag("option"))
        {
            parent.route()
        }.iterator()
}