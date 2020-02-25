// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.WebElement

public interface Sequencer {
    public fun preInteract(tag: WebElement)
    public fun postInteract(tag: WebElement)
}