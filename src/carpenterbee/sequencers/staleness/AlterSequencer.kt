// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.staleness

import carpenterbee.functionality.waiting.Waiter
import carpenterbee.sequencers.Sequencer
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement

public open class AlterSequencer(public val wait: Waiter = Waiter()) :
    Sequencer {
    protected lateinit var stored: WebElement

    public override fun preInteract(tag: WebElement) {
        stored = tag
    }

    public override fun postInteract(tag: WebElement) {
        try {
            wait.until(stored) { stale }
        } catch (e: UninitializedPropertyAccessException) {
            throw IllegalStateException(
                "Attempted to call ${::postInteract.name} function of ${::AlterSequencer.name} " +
                        "without first calling ${::preInteract.name}", e
            )
        } catch (e: TimeoutException) {
            throw TimeoutException("Timed out waiting for element to change after interaction.", e)
        }
    }

    private val WebElement.stale: Boolean
        get() {
            return try {
                val enabled = isEnabled
                enabled && !enabled // always false
            } catch (_: StaleElementReferenceException) {
                true
            }
        }
}