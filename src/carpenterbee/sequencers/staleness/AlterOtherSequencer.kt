// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.staleness

import carpenterbee.functionality.waiting.Waiter
import org.openqa.selenium.WebElement

public class AlterOtherSequencer(private val readTag: () -> WebElement, wait: Waiter = Waiter()) :
    AlterSequencer(wait) {
    public override fun preInteract(tag: WebElement) {
        stored = readTag()
    }
}