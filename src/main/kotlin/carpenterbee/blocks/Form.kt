// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.functionality.scriptReturn

public open class Form<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public val target: String?
        get() = tag.getAttribute("target")

    public val data: Iterable<Pair<String, String>>
        get() = tag.let {
            it.scriptReturn<Array<Array<String>>>("Array.from(new FormData(arguments[0]));", it)
        }.map { it[0] to it[1] }
}
