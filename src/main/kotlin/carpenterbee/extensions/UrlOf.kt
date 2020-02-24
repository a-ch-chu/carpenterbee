// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.extensions

import carpenterbee.Element
import java.net.URL

public fun <TElement : Element> TElement.urlOf(property: TElement.() -> String?) =
    property()?.let { URL(URL(session.driver.currentUrl), it) }