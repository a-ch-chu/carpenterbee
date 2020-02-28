// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.focus

import carpenterbee.construction.javascript.voidProperty
import carpenterbee.sequencers.Sequencer
import carpenterbee.WebElement

public object BlurSequencer : Sequencer {
    public override fun preInteract(tag: WebElement) {}

    public override fun postInteract(tag: WebElement) =
        tag.voidProperty("blur()")
}