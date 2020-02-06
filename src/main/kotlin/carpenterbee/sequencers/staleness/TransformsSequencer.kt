// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.staleness

import carpenterbee.functionality.waiting.Wait
import carpenterbee.sequencers.Sequencer
import carpenterbee.sequencers.SequencerException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebElement

public open class TransformsSequencer(public val wait: Wait = Wait()) : Sequencer {
    protected lateinit var stored: WebElement

    public override fun preInteract(tag: WebElement) {
        stored = tag
    }

    public override fun postInteract(tag: WebElement) {
        if (!wait.until { ::stored.isInitialized && stored.stale })
            throw SequencerException("Timed out waiting for DOM mutations to occur after interaction.")
    }

    private val WebElement.stale: Boolean
        get() {
            return try {
                poke()
                false
            } catch (_: StaleElementReferenceException) {
                true
            }
        }

    private fun WebElement.poke() {
        isEnabled
    }
}