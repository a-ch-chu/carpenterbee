// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.focus

import carpenterbee.sequencers.Sequencer
import carpenterbee.WebElement
import carpenterbee.construction.JavaScript.voidMember

public object FocusSequencer : Sequencer {
    public override fun preInteract(tag: WebElement) {
        tag.voidMember("focus()")
    }

    public override fun postInteract(tag: WebElement) {}
}