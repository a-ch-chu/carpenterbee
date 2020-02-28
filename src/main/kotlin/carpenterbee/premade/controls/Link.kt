// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.premade.controls

import carpenterbee.*
import carpenterbee.premade.HasText

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Link(parent: TParent, specifier: By) =
    Link(parent, specifier, ::toParent)

public open class Link<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Button<TParent, TDefaultTo>(parent, specifier, route),
    HasText {

    public override val text: String
        get() = read { text }

    public val reference: String
        get() = read("href")
}