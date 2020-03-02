// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.sequencers

import carpenterbee.WebElement

public class SequencerList private constructor(
    private val wrappedList: MutableList<Sequencer>,
    public val postOrder: Stacking = Stacking.FIFO
) : MutableList<Sequencer> by wrappedList {
    public constructor(order: Stacking = Stacking.FIFO)
            : this(mutableListOf(), order)

    public fun sequence(tag: WebElement, interaction: WebElement.() -> Unit) {
        wrappedList.forEach { it.preInteract(tag) }

        tag.interaction()

        fun Iterable<Sequencer>.postInteract() = forEach { it.postInteract(tag) }
        when (postOrder) {
            Stacking.FIFO -> wrappedList.postInteract()
            Stacking.FILO -> wrappedList.reversed().postInteract()
        }
    }

    public enum class Stacking {
        FIFO,
        FILO
    }
}