// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls

import carpenterbee.*
import carpenterbee.controls.traits.HasText
import carpenterbee.functionality.value

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Field(parent: TParent, specifier: By) = Field(parent, specifier, { it })

public open class Field<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Control<TParent, TDefaultRoute>(parent, specifier, route), HasText {
    public override val text: String get() = read { value ?: "" }

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