// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.Element

public fun <TElement : Element> TElement.verifyThat(verification: TElement.() -> Unit): TElement =
    this.apply(verification)

public fun <TElement : Element> TElement.verifyThat(fact: String, verification: TElement.() -> Boolean): TElement {
    if (!this.verification()) {
        throw VerificationError("Unable to verify that $fact.")
    }
    return this
}

public open class VerificationError : AssertionError {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
}