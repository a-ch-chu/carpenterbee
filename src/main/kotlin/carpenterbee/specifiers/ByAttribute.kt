// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.specifiers

import org.openqa.selenium.By

public fun Specifiers.attribute(key: String, value: Any?) = ByAttribute(key, value)

public class ByAttribute(val key: String, val value: Any?) : By.ByCssSelector("[$key='${value.toString()}']") {
    public override fun toString(): String = "By.attribute $key = $value"
}