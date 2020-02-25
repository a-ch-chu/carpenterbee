// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*

public open class Image<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public val source: String?
        get() = tag.getAttribute("src")
}