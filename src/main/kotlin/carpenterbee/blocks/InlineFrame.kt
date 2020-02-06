// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.Block
import carpenterbee.Section
import carpenterbee.functionality.getOrNull
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

public open class InlineFrame<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public override val scope: SearchContext?
        get() = getOrNull().run {
            if (this == null) null
            else session.driver.switchTo().frame(this)
        }
}