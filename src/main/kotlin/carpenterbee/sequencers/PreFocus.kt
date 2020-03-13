// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.WebElement
import carpenterbee.construction.JavaScript.voidMember

public object PreFocus : Sequencer {
    public override fun preInteract(tag: WebElement) {
        tag.voidMember("focus()")
    }

    public override fun postInteract(tag: WebElement) {}
}
