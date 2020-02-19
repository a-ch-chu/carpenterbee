// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.Block
import carpenterbee.Section
import carpenterbee.extensions.getOrNull
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

public open class InlineFrame<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public override val scope: SearchContext?
        get() = getOrNull()?.let { session.driver.switchTo().frame(it) }
}