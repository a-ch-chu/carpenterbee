// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

@Suppress("FunctionName") // Factory function
inline fun <reified TExpected> JavascriptReturnException(forObject: Any) =
    JavascriptReturnException(TExpected::class.simpleName, forObject::class.simpleName)

public class JavascriptReturnException(expectedType: String?, actualType: String?) : TypeCastException(
    "Expected javascript execution to return type $expectedType. Instead got return of type: $actualType."
)