// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.Block
import carpenterbee.By

public interface HasParent<TParent : Block> : HasFindTimeout {
    public val parent: TParent
    public val specifier: By
}