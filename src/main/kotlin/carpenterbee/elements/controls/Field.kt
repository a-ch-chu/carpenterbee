// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.elements.controls

import carpenterbee.*
import carpenterbee.elements.HasValue

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Field(parent: TParent, specifier: By) =
    Field(parent, specifier, ::toParent)

public open class Field<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Control<TParent, TDefaultTo>(parent, specifier, route), HasValue {
    public override val value: String get() = read(Tag.value)

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