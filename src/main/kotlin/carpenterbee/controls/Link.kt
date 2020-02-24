// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls

import carpenterbee.Block
import carpenterbee.controls.traits.HasText
import org.openqa.selenium.By
import java.net.URL

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Link(parent: TParent, specifier: By) = Link(parent, specifier, { it })

public open class Link<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Button<TParent, TDefaultRoute>(parent, specifier, route), HasText {
    public override val text: String get() = read { text }

    public val reference: String?
        get() = read { getAttribute("href") }
}