// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.interfaces

import carpenterbee.Block
import carpenterbee.By
import carpenterbee.functionality.TagFinder

public interface IsFindable<TParent : Block> : HasParent<TParent>,
    HasFindTimeout {
    public val specifier: By

    public val present: Boolean get() = !absent
    public val absent: Boolean
        get() = TagFinder.findOrNull(this) == null
}