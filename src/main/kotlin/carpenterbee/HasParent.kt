// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import org.openqa.selenium.By

public interface HasParent<TParent : Block> : HasFindTimeout {
    public val parent: TParent
    public val specifier: By
}