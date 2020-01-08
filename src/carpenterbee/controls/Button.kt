// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls

import carpenterbee.*
import org.openqa.selenium.By

@Suppress("FunctionName") // Factory function
public fun <TParent : Block> Button(parent: TParent, specifier: By) = Button(parent, specifier, { it })

public open class Button<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Control<TParent, TDefaultRoute>(parent, specifier, route) {
    public fun click() = click(route)
    public fun <TRouteTo : Block> click(route: (TParent) -> TRouteTo) = interact(route) { click() }
}