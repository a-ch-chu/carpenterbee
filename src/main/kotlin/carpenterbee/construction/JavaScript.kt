// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.construction

import carpenterbee.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WrapsDriver
import org.openqa.selenium.WrapsElement

object JavaScript {
    public fun WebElement.getJavascriptExecutor(): JavascriptExecutor {
        var element = this
        while (element is WrapsElement) element = element.wrappedElement
        return (element as WrapsDriver).wrappedDriver as JavascriptExecutor
    }

    public fun WebElement.voidMember(property: String) {
        executeVoid("arguments[0].$property;", this)
    }

    public fun WebElement.executeVoid(script: String, vararg args: Any?) {
        scriptReturn<Any>(script, *args)
    }

    public inline fun <reified TReturn : Any> WebElement.memberReturn(
        property: String
    ): TReturn =
        scriptReturn("arguments[0].$property;", this)

    public inline fun <reified TReturn : Any> WebElement.scriptReturn(
        script: String,
        vararg args: Any?
    ): TReturn =
        this.getJavascriptExecutor()
            .executeScript("return $script", *args)
            .let {
                it as? TReturn ?: throw JavascriptReturnException<TReturn>(
                    it
                )
            }

    @Suppress("FunctionName") // Factory function
    public inline fun <reified TExpected> JavascriptReturnException(forObject: Any) =
        JavascriptReturnException(
            TExpected::class.simpleName,
            forObject::class.simpleName
        )

    public class JavascriptReturnException(
        expectedType: String?,
        actualType: String?
    ) : TypeCastException(
        "Expected javascript execution to return type $expectedType. " +
                "Instead got return of type: $actualType."
    )
}