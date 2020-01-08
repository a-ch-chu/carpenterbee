// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.Block
import carpenterbee.Page
import carpenterbee.Session
import carpenterbee.functionality.interfaces.HasFindTimeout
import carpenterbee.functionality.waiting.ElementFinder
import org.openqa.selenium.Alert

@Suppress("FunctionName") // Factory function
public fun <TDefaultRoute : Block> AlertDialog(session: Session, route: (Block) -> TDefaultRoute) =
    AlertDialog(session, route, route)

public class AlertDialog<TAcceptRoute : Block, TDismissRoute : Block>(
    session: Session,
    private val acceptRoute: (Block) -> TAcceptRoute,
    private val dismissRoute: (Block) -> TDismissRoute
) : Page(session), HasFindTimeout {
    public val alert get() = ElementFinder.waitToGet(findTimeout) { session.driver.switchTo().alert() }

    public fun <TRouteTo> interact(route: (Block) -> TRouteTo, interaction: Alert.() -> Unit): TRouteTo {
        alert.interaction()
        return route(this)
    }

    public fun accept() = accept(acceptRoute)
    public fun <TRouteTo> accept(route: (Block) -> TRouteTo) = interact(route) { accept() }

    public fun dismiss() = dismiss(dismissRoute)
    public fun <TRouteTo> dismiss(route: (Block) -> TRouteTo) = interact(route) { dismiss() }

    public fun enterText(text: String): AlertDialog<TAcceptRoute, TDismissRoute> = apply { alert.sendKeys(text) }

    public val message: String get() = alert.text
}