// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.focus

import carpenterbee.sequencers.Sequencer
import carpenterbee.WebElement
import carpenterbee.construction.JavaScript.voidMember

public object BlurSequencer : Sequencer {
    public override fun preInteract(tag: WebElement) {}

    public override fun postInteract(tag: WebElement) =
        tag.voidMember("blur()")
}