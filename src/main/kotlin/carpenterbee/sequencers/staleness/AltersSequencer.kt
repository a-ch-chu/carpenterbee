// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.staleness

import carpenterbee.functionality.waiting.Wait
import org.openqa.selenium.WebElement

public class AltersSequencer(private val readTag: () -> WebElement, wait: Wait = Wait()) :
    TransformsSequencer(wait) {
    public override fun preInteract(tag: WebElement) {
        stored = readTag()
    }
}