// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import carpenterbee.extensions.getOrNull

public abstract class SubPage(session: Session) : Page(session) {
    public abstract val specifier: By

    public override val scope: SearchContext?
        get() = super.scope.getOrNull(specifier)
}