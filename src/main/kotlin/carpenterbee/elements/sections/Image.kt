// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.elements.sections

import carpenterbee.*
import carpenterbee.elements.HasText

public open class Image<TParent : Block>(parent: TParent, specifier: By) :
    Section<TParent>(parent, specifier), HasText {
    public val source: String
        get() = readTag("src")

    override val text: String
        get() = readTag("alt")
}