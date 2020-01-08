// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.functionality.executeScript
import carpenterbee.functionality.waiting.Waiter
import org.openqa.selenium.WebElement

public class JQuerySequencer(public val wait: Waiter = Waiter()) : Sequencer {
    override fun preInteract(tag: WebElement) {}

    override fun postInteract(tag: WebElement) {
        wait.until(tag) { executeScript<Boolean>("return !window['jQuery'] || !jQuery.active;") }
    }
}