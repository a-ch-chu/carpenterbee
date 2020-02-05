// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

public abstract class SubPage(session: Session) : Page(session) {
    public abstract val specifier: By

    public override val scope: SearchContext?
        get() = super.scope.findOrNull(specifier)
}