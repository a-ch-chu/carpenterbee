// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.WebElement
import carpenterbee.construction.JavaScript.voidMember

public object PostBlur : Sequencer {
    public override fun preInteract(tag: WebElement) {}

    public override fun postInteract(tag: WebElement) =
        tag.voidMember("blur()")
}