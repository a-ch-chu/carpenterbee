// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import org.openqa.selenium.WebElement

public class FunctionSequencer(
    private val preInteraction: (WebElement) -> Unit,
    private val postInteraction: (WebElement) -> Unit
) : Sequencer {
    public override fun preInteract(tag: WebElement) = preInteraction(tag)
    public override fun postInteract(tag: WebElement) = postInteraction(tag)
}