// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.premade

import carpenterbee.*
import carpenterbee.functionality.getTagOrNull

public abstract class SubPage(session: Session) : Page(session) {
    public abstract val specifier: By

    public override val scope: SearchContext?
        get() = super.scope.getTagOrNull(specifier)
}