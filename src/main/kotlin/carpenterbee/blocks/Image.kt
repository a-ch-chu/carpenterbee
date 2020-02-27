// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.controls.traits.HasText
import carpenterbee.functionality.getAttributeOrThrow

public open class Image<TParent : Block>(parent: TParent, specifier: By) :
    Section<TParent>(parent, specifier), HasText {
    public val source: String
        get() = tag.getAttributeOrThrow("src")

    override val text: String
        get() = tag.getAttributeOrThrow("alt")
}