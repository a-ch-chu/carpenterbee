// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.elements.controls.options

import carpenterbee.*
import carpenterbee.construction.getLabel
import carpenterbee.elements.HasText

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Checkbox(parent: TParent, specifier: By) =
    Checkbox(parent, specifier, ::toParent)

public open class Checkbox<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Option<TParent, TDefaultTo>(parent, specifier, route),
    HasText {
    public override val text get() = getLabel()
}