// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.Block
import carpenterbee.By
import carpenterbee.controls.traits.HasText

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> RadioButton(parent: TParent, specifier: By) =
    RadioButton(parent, specifier, { it })

public open class RadioButton<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Response<TParent, TDefaultRoute>(parent, specifier, route), HasText {
    public override val text get() = getLabel()
}