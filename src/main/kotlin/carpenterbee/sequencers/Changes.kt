// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.*
import carpenterbee.functionality.waiting.Wait
import org.openqa.selenium.StaleElementReferenceException

public class Changes(
    private val readTag: (() -> WebElement)? = null,
    public val wait: Wait = Wait()
) : Sequencer {
    private lateinit var stored: WebElement

    public constructor(element: Control<*, *>, wait: Wait = Wait())
            : this({ element.read { this } }, wait)

    public constructor(element: Section<*>, wait: Wait = Wait())
            : this({ element.tag }, wait)

    public override fun preInteract(tag: WebElement) {
        stored = readTag?.invoke() ?: tag
    }

    public override fun postInteract(tag: WebElement) {
        if (!::stored.isInitialized)
            throw SequencerException(
                "Tried to call post-interaction sequencing before pre-interaction."
            )

        if (!wait.until { stored.stale })
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