// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.controls.traits.HasText
import carpenterbee.functionality.HasFindTimeout
import carpenterbee.functionality.TagFinder
import org.openqa.selenium.Alert
import org.openqa.selenium.NoAlertPresentException

@Suppress("FunctionName") // Factory function
public fun <TDefaultRoute : Block> AlertDialog(
    session: Session, route: (Block) -> TDefaultRoute
) = AlertDialog(session, route, route)

public class AlertDialog<TAcceptTo : Block, TDismissTo : Block>(
    session: Session,
    private val acceptRoute: (Block) -> TAcceptTo,
    private val dismissRoute: (Block) -> TDismissTo
) : Page(session), HasFindTimeout, HasText {
    public val alert: Alert
        get() = TagFinder.find(this, ::getAlertOrNull)

    public fun getAlertOrNull(): Alert? =
        try {
            session.driver.switchTo().alert()
        } catch (e: NoAlertPresentException) {
            null
        }

    public val present: Boolean get() = !absent
    public val absent: Boolean
        get() = TagFinder.findOrNull(this, ::getAlertOrNull) == null

    public override val text: String get() = alert.text

    public fun interact(
        interaction: Alert.() -> Unit
    ): AlertDialog<TAcceptTo, TDismissTo> = interact(::i, interaction)

    public fun <TRouteTo> interact(
        route: (AlertDialog<TAcceptTo, TDismissTo>) -> TRouteTo,
        interaction: Alert.() -> Unit
    ): TRouteTo {
        alert.interaction()
        return route(this)
    }

    public fun accept(): TAcceptTo = accept(acceptRoute)
    public fun <TRouteTo> accept(route: (Block) -> TRouteTo): TRouteTo =
        interact(route) { accept() }

    public fun dismiss(): TDismissTo = dismiss(dismissRoute)
    public fun <TRouteTo> dismiss(route: (Block) -> TRouteTo): TRouteTo =
        interact(route) { dismiss() }

    public fun enterText(text: String): AlertDialog<TAcceptTo, TDismissTo> =
        interact { sendKeys(text) }
}