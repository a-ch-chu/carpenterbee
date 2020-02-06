// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.extensions.executeScript
import carpenterbee.functionality.waiting.Wait
import org.openqa.selenium.WebElement

public class JQuerySequencer(public val wait: Wait = Wait()) : Sequencer {
    override fun preInteract(tag: WebElement) {}

    override fun postInteract(tag: WebElement) {
        if (!wait.until { tag.executeScript<Boolean>("return !window['jQuery'] || !jQuery.active;") })
            throw SequencerException("Timed out waiting for jQuery activity to subside after interaction.")
    }
}