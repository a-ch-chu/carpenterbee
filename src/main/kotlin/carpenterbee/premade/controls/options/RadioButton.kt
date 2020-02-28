// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.premade.controls.options

import carpenterbee.*
import carpenterbee.construction.getLabel
import carpenterbee.premade.HasText

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> RadioButton(parent: TParent, specifier: By) =
    RadioButton(parent, specifier, ::toParent)

public open class RadioButton<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Selectable<TParent, TDefaultTo>(parent, specifier, route),
    HasText {
    public override val text: String get() = getLabel()
}