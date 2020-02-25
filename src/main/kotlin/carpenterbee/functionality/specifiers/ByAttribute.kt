// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.specifiers

import org.openqa.selenium.By.ByCssSelector

public fun Specifiers.attribute(key: String, value: Any?) = ByAttribute(key, value)

public class ByAttribute(val key: String, val value: Any?) : ByCssSelector("[$key='$value']") {
    public override fun toString(): String = "By.attribute $key = $value"
}