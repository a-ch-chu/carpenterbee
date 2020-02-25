// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.functionality.executeScript

public open class Form<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public val target: String?
        get() = tag.getAttribute("target")

    public val data: Iterable<Pair<String, String>>
        get() = tag.executeScript<Array<Array<String>>>(
            "return Array.from(new FormData(arguments[0]));"
        ).map { it[0] to it[1] }
}
