// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.premade.sections

import carpenterbee.*
import carpenterbee.functionality.getTagOrNull

public open class InlineFrame<TParent : Block>(parent: TParent, specifier: By) :
    Section<TParent>(parent, specifier) {

    public override val scope: SearchContext?
        get() = getTagOrNull()?.let(session.driver.switchTo()::frame)
}