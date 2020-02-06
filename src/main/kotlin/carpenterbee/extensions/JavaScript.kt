// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.WrapsDriver
import org.openqa.selenium.WrapsElement

public fun WebElement.getJavascriptExecutor(): JavascriptExecutor {
    var element = this
    while (element is WrapsElement) element = element.wrappedElement
    return (element as WrapsDriver).wrappedDriver as JavascriptExecutor
}

internal fun WebElement.executeScript(script: String, vararg args: Any?) {
    executeScript<Any>(script, *args)
}

public inline fun <reified TReturn : Any> WebElement.executeScript(script: String, vararg args: Any?): TReturn =
    this.getJavascriptExecutor().executeScript(script, *args).run {
        this as? TReturn ?: throw JavascriptReturnException<TReturn>(this)
    }

public inline fun <reified TReturn : Any> WebElement.javascriptProperty(property: String): TReturn =
    this.getJavascriptExecutor().executeScript("return arguments[0].$property;", this).run {
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