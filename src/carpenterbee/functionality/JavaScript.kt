// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.WrapsDriver
import org.openqa.selenium.WrapsElement

internal fun WebElement.getJavascriptExecutor(): JavascriptExecutor {
    var element = this
    while (element is WrapsElement) element = element.wrappedElement
    return (element as WrapsDriver).wrappedDriver as JavascriptExecutor
}

internal fun WebElement.executeScript(script: String, vararg args: Any?) {
    executeScript<Any>(script, *args)
}

internal inline fun <reified TReturn : Any> WebElement.executeScript(script: String, vararg args: Any?): TReturn =
    this.getJavascriptExecutor().executeScript(script, *args).run {
        this as? TReturn ?: throw JavascriptReturnException<TReturn>(this)
    }

internal inline fun <reified TReturn : Any> WebElement.javascriptProperty(property: String): TReturn =
    this.getJavascriptExecutor().executeScript("return arguments[0].$property;", this).run {
        this as? TReturn ?: throw JavascriptReturnException<TReturn>(this)
    }
