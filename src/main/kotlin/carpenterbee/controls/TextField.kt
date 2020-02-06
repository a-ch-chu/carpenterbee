// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls

import carpenterbee.Block
import carpenterbee.Control
import carpenterbee.controls.traits.HasText
import carpenterbee.controls.traits.HasValue
import carpenterbee.functionality.Tag
import org.openqa.selenium.By

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> TextField(parent: TParent, specifier: By) = TextField(parent, specifier, { it })

public open class TextField<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Control<TParent, TDefaultRoute>(parent, specifier, route), HasText, HasValue {
    public override val text: String get() = read { text }
    public override val value: String get() = read { getAttribute(Tag.value) }

    public fun enter(text: Any?) = enter(text, route)
    public fun <TRouteTo : Block> enter(text: Any?, route: (TParent) -> TRouteTo) =
        interact(route) {
            clear()
            sendKeys(text.toString())
        }

    public fun append(text: Any?) = append(text, route)
    public fun <TRouteTo : Block> append(text: Any?, route: (TParent) -> TRouteTo) =
        interact(route) { sendKeys(text.toString()) }
}