// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

private const val getterPrefix = "get"

public fun Any.nameOfInitialisingProperty(): String =
    Thread.currentThread().stackTrace.drop(1)
        .map { it.methodName ?: "" }
        .firstOrNull { it.startsWith(getterPrefix) }
        ?.removePrefix(getterPrefix)
        ?: "Unnamed ${this::class.simpleName}"