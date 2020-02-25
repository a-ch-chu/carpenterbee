// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.extensions.getOrNull

public open class InlineFrame<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public override val scope: SearchContext?
        get() = getOrNull()?.let(session.driver.switchTo()::frame)
}