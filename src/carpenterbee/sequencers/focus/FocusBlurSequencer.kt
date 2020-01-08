// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers.focus

import carpenterbee.sequencers.Sequencer
import org.openqa.selenium.WebElement

public object FocusBlurSequencer : Sequencer {
    public override fun preInteract(tag: WebElement) =
        FocusSequencer.preInteract(tag)
    public override fun postInteract(tag: WebElement) = BlurSequencer.postInteract(tag)
}