// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.focus

import carpenterbee.functionality.executeScript
import carpenterbee.sequencers.Sequencer
import org.openqa.selenium.WebElement

public object FocusSequencer : Sequencer {
    public override fun preInteract(tag: WebElement) {
        tag.executeScript("arguments[0].focus();", tag)
    }

    public override fun postInteract(tag: WebElement) {}
}