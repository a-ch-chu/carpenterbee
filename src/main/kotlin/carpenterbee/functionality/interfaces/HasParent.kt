// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.interfaces

import carpenterbee.Block

public interface HasParent<TParent : Block> {
    public val parent: TParent
}