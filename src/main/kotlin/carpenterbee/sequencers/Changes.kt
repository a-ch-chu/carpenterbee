// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.WebElement
import carpenterbee.functionality.waiting.Wait
import org.openqa.selenium.StaleElementReferenceException

public class Changes(
    private val readTag: (() -> WebElement)? = null,
    public val wait: Wait = Wait()
) : Sequencer {
    private lateinit var stored: WebElement

    public override fun preInteract(tag: WebElement) {
        stored = readTag?.invoke() ?: tag
    }

    public override fun postInteract(tag: WebElement) {
        if (!wait.until { ::stored.isInitialized && stored.stale })
            throw SequencerException(
                "Timed out waiting for DOM mutations to occur after interaction."
            )
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