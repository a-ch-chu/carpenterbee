// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.Block
import carpenterbee.By

public interface IsFindable<TParent : Block> : HasParent<TParent>, HasFindTimeout {
    public val specifier: By

    public val present: Boolean get() = !absent
    public val absent: Boolean
        get() = TagFinder.findOrNull(this) == null
}