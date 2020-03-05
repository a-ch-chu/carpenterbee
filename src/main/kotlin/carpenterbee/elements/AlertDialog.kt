// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.elements

import carpenterbee.*
import carpenterbee.functionality.interfaces.HasFindTimeout
import carpenterbee.functionality.TagFinder
import org.openqa.selenium.Alert
import org.openqa.selenium.NoAlertPresentException

@Suppress("FunctionName") // Factory function
public fun <TDefaultTo : Block> AlertDialog(
    route: (Block) -> TDefaultTo
): Block.() -> AlertDialog<TDefaultTo, TDefaultTo> = {
    AlertDialog(this, route, route)
}

@Suppress("FunctionName") // Factory function
public fun <TAcceptTo : Block, TDismissTo : Block> AlertDialog(
    acceptRoute: (Block) -> TAcceptTo,
    dismissRoute: (Block) -> TDismissTo
): Block.() -> AlertDialog<TAcceptTo, TDismissTo> = {
    AlertDialog(this, acceptRoute, dismissRoute)
}

public class AlertDialog<TAcceptRoute : Block, TDismissRoute : Block>(
    host: Block,
    private val acceptRoute: (Block) -> TAcceptRoute,
    private val dismissRoute: (Block) -> TDismissRoute
) : Page(host.session), HasFindTimeout, HasText {
    public val alert: Alert
        get() = TagFinder.find(this, ::getAlertOrNull)

    public override val name =
        "${this::class.simpleName} from ${host::class.simpleName}"

    public fun getAlertOrNull(): Alert? =
        try {
            session.driver.switchTo().alert()
        } catch (_: NoAlertPresentException) {
            null
        }

    public val present: Boolean get() = !absent
    public val absent: Boolean
        get() = TagFinder.findOrNull(this, ::getAlertOrNull) == null

    public override val text: String get() = alert.text

    public fun <TRouteTo> interact(
        route: (AlertDialog<TAcceptRoute, TDismissRoute>) -> TRouteTo,
        interaction: Alert.() -> Unit
    ): TRouteTo {
        alert.interaction()
        return route(this)
    }

    public fun accept(): TAcceptRoute = accept(acceptRoute)
    public fun <TRouteTo> accept(route: (Block) -> TRouteTo): TRouteTo =
        interact(route) { accept() }

    public fun dismiss(): TDismissRoute = dismiss(dismissRoute)
    public fun <TRouteTo> dismiss(route: (Block) -> TRouteTo): TRouteTo =
        interact(route) { dismiss() }

    public fun enterText(text: String): AlertDialog<TAcceptRoute, TDismissRoute> =
        apply { alert.sendKeys(text) }
}