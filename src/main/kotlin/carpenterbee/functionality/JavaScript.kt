// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import carpenterbee.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WrapsDriver
import org.openqa.selenium.WrapsElement

public fun WebElement.getJavascriptExecutor(): JavascriptExecutor {
    var element = this
    while (element is WrapsElement) element = element.wrappedElement
    return (element as WrapsDriver).wrappedDriver as JavascriptExecutor
}

public fun WebElement.voidProperty(property: String) {
    executeVoid("arguments[0].$property;", this)
}

public fun WebElement.executeVoid(script: String, vararg args: Any?) {
    scriptReturn<Any>(script, *args)
}

public inline fun <reified TReturn : Any> WebElement.scriptProperty(property: String): TReturn =
    scriptReturn("arguments[0].$property;", this)

public inline fun <reified TReturn : Any> WebElement.scriptReturn(script: String, vararg args: Any?): TReturn =
    this.getJavascriptExecutor().executeScript("return $script", *args).run {
        this as? TReturn ?: throw JavascriptReturnException<TReturn>(this)
    }

@Suppress("FunctionName") // Factory function
inline fun <reified TExpected> JavascriptReturnException(forObject: Any) =
    JavascriptReturnException(
        TExpected::class.simpleName,
        forObject::class.simpleName
    )

public class JavascriptReturnException(expectedType: String?, actualType: String?) : TypeCastException(
    "Expected javascript execution to return type $expectedType. Instead got return of type: $actualType."
)