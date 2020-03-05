// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.interfaces

interface MayBePresent {
    public val absent: Boolean get() = !present
    public val present: Boolean
}