// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.Block
import carpenterbee.Page
import carpenterbee.functionality.interfaces.HasParent

private const val getterPrefix = "get"

public fun Any.deriveName(): String =
    Thread.currentThread().stackTrace.drop(1)
        .map { it.methodName ?: "" }
        .firstOrNull { it.startsWith(getterPrefix) }
        ?.removePrefix(getterPrefix)
        ?: "Unknown ${className ?: "Element"}"

public val Any.className: String?
    get() = this::class.simpleName

public fun Block.rootPageName(): String {
    var current: Block = this
    while (current is HasParent<*>) {
        current = current.parent
    }
    return (current as? Page)?.className ?: "Unknown Page"
}