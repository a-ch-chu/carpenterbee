// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.interfaces

import carpenterbee.Block
import carpenterbee.By
import carpenterbee.functionality.TagFinder

public interface IsFindable<TParent : Block> :
    HasParent<TParent>,
    HasFindTimeout,
    MayBePresent {
    public val specifier: By

    public override val present: Boolean
        get() = TagFinder.findOrNull(this) != null
}