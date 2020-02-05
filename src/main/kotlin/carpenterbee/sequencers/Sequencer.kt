// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import org.openqa.selenium.WebElement

public interface Sequencer {
    public fun preInteract(tag: WebElement)
    public fun postInteract(tag: WebElement)
}