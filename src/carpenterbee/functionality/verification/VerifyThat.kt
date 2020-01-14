// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.verification

import carpenterbee.Element

public fun <TElement : Element> TElement.verifyThat(verification: TElement.() -> Unit): TElement =
    this.apply(verification)

public fun <TElement : Element> TElement.verifyThat(fact: String, verification: TElement.() -> Boolean): TElement {
    if (!this.verification()) {
        throw VerificationError("Unable to verify that $fact")
    }
    return this
}