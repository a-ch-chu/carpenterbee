// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

public open class InlineFrame<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public override val scope: SearchContext?
        get() {
            val subScope = parent.scope.findOrNull(specifier)
            return if (subScope == null)
                null
            else
                session.driver.switchTo().frame(subScope)
        }
}