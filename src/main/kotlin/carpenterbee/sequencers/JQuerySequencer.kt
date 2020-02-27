// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.functionality.scriptReturn
import carpenterbee.functionality.waiting.Wait
import carpenterbee.WebElement

public class JQuerySequencer(public val wait: Wait = Wait()) : Sequencer {
    override fun preInteract(tag: WebElement) {}

    override fun postInteract(tag: WebElement) {
        val timedOut = !wait.until { 
            tag.scriptReturn("!window['jQuery'] || !jQuery.active;")
        }
        if (timedOut)
            throw SequencerException(
                "Timed out waiting for jQuery activity to subside after interaction."
            )
    }
}