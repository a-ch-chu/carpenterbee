// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.focus

import carpenterbee.functionality.voidProperty
import carpenterbee.sequencers.Sequencer
import carpenterbee.WebElement

public object FocusSequencer : Sequencer {
    public override fun preInteract(tag: WebElement) {
        tag.voidProperty("focus()")
    }

    public override fun postInteract(tag: WebElement) {}
}